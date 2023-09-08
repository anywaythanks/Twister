package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.config.MapstructConfig;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ActualCaseRepository;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Supplier;

import static com.github.anywaythanks.twisterresource.services.managers.CaseActualInformationServiceTest.Cortege.c;
import static java.time.temporal.ChronoUnit.HOURS;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(MapstructConfig.class)
class CaseActualInformationServiceTest {
    @Autowired
    @Spy
    CaseMapper caseMapper;
    @Autowired
    GeneralAccountMapper generalAccountMapper;
    @Autowired
    @Spy
    PageMapper pageMapper;
    @Mock
    CaseRepository caseRepository;
    @Mock
    ActualCaseRepository actualCaseRepository;
    @Mock
    GeneralAccountInformationService generalAccountInformationService;
    @Mock
    CaseSlotInformationService caseSlotInformationService;
    MoneyType type;
    GeneralAccount generalAccount;
    Clock clock;
    CaseActualInformationService caseActualInformationService;
    Random random;

    record Cortege(Case aCase, long ofEpochSecond) {
        public static Cortege c(Case aCase, long epochMilli) {
            return new Cortege(aCase, epochMilli);
        }
    }

    List<Case> restructuredCases(List<Case> cases, Comparator<Case> sortBy, TemporalUnit unit, long... cooldowns) {
        Iterator<Case> it = cases.iterator();
        return Arrays.stream(cooldowns)
                .mapToObj(cooldown -> {
                    Case nCase = it.next();
                    Long id = nCase.getId();
                    return Case.builder()
                            .id(id)
                            .name("case-" + id)
                            .visibleName("v" + id)
                            .cooldown(Duration.of(Math.max(0, cooldown), unit))
                            .createdOn(nCase.getCreatedOn())
                            .modifiedBy(nCase.getModifiedBy())
                            .description(nCase.getDescription())
                            .price(nCase.getPrice())
                            .build();
                })
                .sorted(sortBy)
                .toList();
    }

    List<Case> generateCases(Supplier<Long> generatorId, Comparator<Case> sortBy, TemporalUnit unit, long... cooldowns) {
        Money price = Money.builder().moneyType(type).value(BigDecimal.ZERO).build();
        Money delta = Money.builder().moneyType(type).value(BigDecimal.ONE).build();

        return Arrays.stream(cooldowns)
                .mapToObj(cooldown -> {
                    Long id = generatorId.get();
                    return Case.builder()
                            .id(id)
                            .name("case-" + id)
                            .visibleName("v" + id)
                            .cooldown(Duration.of(cooldown, unit))
                            .createdOn(Instant.now(clock))
                            .modifiedBy(Instant.now(clock).plus(10, SECONDS))
                            .description("" + random.nextInt(1000))
                            .price(price.add(delta))
                            .build();
                })
                .sorted(sortBy)
                .toList();
    }

    List<CaseLastTwistResponseDto> sortTwists(long startId, long endId, Comparator<Case> sortBy, Cortege... twists) {
        return Arrays.stream(twists)
                .filter(twist -> twist.aCase.getId() >= startId && twist.aCase.getId() <= endId)
                .sorted((c1, c2) -> sortBy.compare(c1.aCase(), c2.aCase()))
                .map(c -> new CaseLastTwistResponseDto(c.aCase().getId(), Instant.ofEpochSecond(c.ofEpochSecond())))
                .toList();
    }

    void initNow(long expected) {
        random = new Random();
        clock = Clock.fixed(Instant.ofEpochSecond(expected), ZoneId.of("Z"));
    }

    void initMocks() {
        caseActualInformationService = new CaseActualInformationService(caseRepository, actualCaseRepository, caseMapper, pageMapper, generalAccountInformationService, caseSlotInformationService, clock);
    }

    void initData() {
        GeneralAccountName generalAccountName = GeneralAccountName.builder()
                .name("g5")
                .build();
        generalAccount = GeneralAccount.builder()
                .id(5L)
                .userUuid("faaa340c-a418-4ae6-b5eb-c18976d7b171")
                .name(generalAccountName)
                .nickname("n5")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        type = MoneyType.builder()
                .id(123)
                .pathToIcon("/45")
                .name("123")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
    }

    @BeforeEach
    void init() {
        initNow(1577836800);//1 January 2020, 0:00:00
        initMocks();
        initData();
    }

    @Nested
    class Full {
        @Test
        void fullWithoutCase() {
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            when(caseRepository.findByName(any())).thenReturn(Optional.empty());
            CaseNameRequestDto caseName = new CaseNameRequestDto("empty");
            NotFoundException exception = assertThrows(NotFoundException.class,
                    () -> caseActualInformationService.getFull(generalName, caseName));
            assertEquals(exception.getMessage(), "Not found.");
        }

        @Test
        void fullWithoutTwists() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.unsorted();
            AtomicLong l = new AtomicLong();
            Case gCase = generateCases(l::getAndIncrement, sort, SECONDS, 3).get(0);
            List<CaseLastTwistResponseDto> twists = List.of();
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            when(caseRepository.findByName(gCase.getName())).thenReturn(Optional.of(gCase));
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), gCase.getId(), gCase.getId(), sortBy)).thenReturn(twists);

            CaseNameRequestDto caseName = caseMapper.toNameRequest(gCase);
            CaseFullDto result = caseActualInformationService.getFull(generalName, caseName);
            CaseFullDto resultEq = caseMapper.toFull(gCase).withCooldown(Duration.ZERO);
            Assertions.assertEquals(resultEq, result);
        }

        @Test
        void full() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.unsorted();
            AtomicLong l = new AtomicLong();
            Case gCase = generateCases(l::getAndIncrement, sort, SECONDS, 3).get(0);
            List<CaseLastTwistResponseDto> twists = sortTwists(0, 0, sort,
                    c(gCase, 1577836798)//31 December 2019, 23:59:58
            );
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            when(caseRepository.findByName(gCase.getName())).thenReturn(Optional.of(gCase));
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), gCase.getId(), gCase.getId(), sortBy)).thenReturn(twists);

            CaseNameRequestDto caseName = caseMapper.toNameRequest(gCase);
            CaseFullDto result = caseActualInformationService.getFull(generalName, caseName);
            CaseFullDto resultEq = caseMapper.toFull(gCase).withCooldown(Duration.of(1, SECONDS));
            Assertions.assertEquals(resultEq, result);
        }
    }

    @Nested
    class Partial {
        @Test
        void partialWithoutCase() {
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            when(caseRepository.findByName(any())).thenReturn(Optional.empty());
            CaseNameRequestDto caseName = new CaseNameRequestDto("empty");
            NotFoundException exception = assertThrows(NotFoundException.class,
                    () -> caseActualInformationService.getPartialItems(generalName, caseName));
            assertEquals(exception.getMessage(), "Not found.");
        }

        @Test
        void partialWithoutTwists() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.unsorted();
            AtomicLong l = new AtomicLong();
            Case gCase = generateCases(l::getAndIncrement, sort, SECONDS, 3).get(0);
            List<CaseLastTwistResponseDto> twists = List.of();
            CaseIdDto caseId = caseMapper.toCaseId(gCase);
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            when(caseRepository.findByName(gCase.getName())).thenReturn(Optional.of(gCase));
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), gCase.getId(), gCase.getId(), sortBy)).thenReturn(twists);
            when(caseSlotInformationService.getPartials(caseId)).thenReturn(List.of());

            CaseNameRequestDto caseName = caseMapper.toNameRequest(gCase);
            CaseItemsPartialResponseDto result = caseActualInformationService.getPartialItems(generalName, caseName);
            CaseItemsPartialResponseDto resultEq = caseMapper.toPartialItemsDTO(List.of(), gCase).withCooldown(Duration.ZERO);
            Assertions.assertEquals(resultEq, result);
        }

        @Test
        void partial() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.unsorted();
            AtomicLong l = new AtomicLong();
            Case gCase = generateCases(l::getAndIncrement, sort, SECONDS, 9).get(0);
            List<CaseLastTwistResponseDto> twists = sortTwists(0, 0, sort,
                    c(gCase, 1577836795)//31 December 2019, 23:59:55
            );
            CaseIdDto caseId = caseMapper.toCaseId(gCase);
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            when(caseRepository.findByName(gCase.getName())).thenReturn(Optional.of(gCase));
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), gCase.getId(), gCase.getId(), sortBy)).thenReturn(twists);
            when(caseSlotInformationService.getPartials(caseId)).thenReturn(List.of());

            CaseNameRequestDto caseName = caseMapper.toNameRequest(gCase);
            CaseItemsPartialResponseDto result = caseActualInformationService.getPartialItems(generalName, caseName);
            CaseItemsPartialResponseDto resultEq = caseMapper.toPartialItemsDTO(List.of(), gCase)
                    .withCooldown(Duration.of(4, SECONDS));
            Assertions.assertEquals(resultEq, result);
        }
    }

    @Nested
    class PageCase {
        @Test
        void pageWithoutTwists() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.by(Sort.Order.desc("id"));
            AtomicLong l = new AtomicLong();
            List<Case> cases = generateCases(l::getAndIncrement, sort, SECONDS, 3);
            List<CaseLastTwistResponseDto> twists = List.of();
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            Page<Case> page = new PageImpl<>(cases);
            when(caseRepository.findAll(PageRequest.of(0, 1, sortBy))).thenReturn(page);
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), 0L, 0L, sortBy)).thenReturn(twists);

            l.set(0);
            CasePagePartialResponseDto result = caseActualInformationService.getPage(0, 1, generalName);
            List<CaseLightPartialResponseDto> casesEq = restructuredCases(cases, sort, SECONDS, 0)
                    .stream()
                    .map(caseMapper::toLightPartialDTO)
                    .toList();
            CasePagePartialResponseDto resultEq = pageMapper.toPartialDTO(casesEq, 1, 0);
            Assertions.assertEquals(resultEq, result);
        }

        @Test
        void page1() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.by(Sort.Order.desc("id"));
            AtomicLong l = new AtomicLong();
            List<Case> cases = generateCases(l::getAndIncrement, sort, SECONDS, 3, 5, 7, 1, 8, 0);
            List<CaseLastTwistResponseDto> twists = sortTwists(0, 5, sort,
                    c(cases.get(0), 1577836798),//31 December 2019, 23:59:58
                    c(cases.get(1), 1577836797),//31 December 2019, 23:59:57
                    c(cases.get(3), 1577836790) //31 December 2019, 23:59:50
            );
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            Page<Case> page = new PageImpl<>(cases);
            when(caseRepository.findAll(PageRequest.of(0, 1, sortBy))).thenReturn(page);
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), 0L, 5L, sortBy)).thenReturn(twists);

            l.set(0);
            CasePagePartialResponseDto result = caseActualInformationService.getPage(0, 1, generalName);
            List<CaseLightPartialResponseDto> casesEq = restructuredCases(cases, sort, SECONDS,
                    1, 2, 0, 0, 0, 0)
                    .stream()
                    .map(caseMapper::toLightPartialDTO)
                    .toList();
            CasePagePartialResponseDto resultEq = pageMapper.toPartialDTO(casesEq, 1, 0);
            Assertions.assertEquals(resultEq, result);
        }

        @Test
        void page2() {
            Comparator<Case> sort = Comparator.comparing(Case::getId);
            Sort sortBy = Sort.by(Sort.Order.desc("id"));
            AtomicLong l = new AtomicLong();
            List<Case> cases = generateCases(l::getAndIncrement, sort, HOURS, 3, 5, 15, 1, 8, 0);
            List<CaseLastTwistResponseDto> twists = sortTwists(0, 5, sort,
                    c(cases.get(0), 1577833200),//31 December 2019, 23:00:00
                    c(cases.get(2), 1577793600),//31 December 2019, 12:00:00
                    c(cases.get(4), 1577815200) //31 December 2019, 18:00:00
            );
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            Page<Case> page = new PageImpl<>(cases);
            when(caseRepository.findAll(PageRequest.of(0, 1, sortBy))).thenReturn(page);
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), 0L, 5L, sortBy)).thenReturn(twists);

            l.set(0);
            CasePagePartialResponseDto result = caseActualInformationService.getPage(0, 1, generalName);
            List<CaseLightPartialResponseDto> casesEq = restructuredCases(cases, sort, HOURS,
                    2, 0, 3, 0, 2, 0)
                    .stream()
                    .map(caseMapper::toLightPartialDTO)
                    .toList();
            CasePagePartialResponseDto resultEq = pageMapper.toPartialDTO(casesEq, 1, 0);
            Assertions.assertEquals(resultEq, result);
        }

        @Test
        void page3() {
            Comparator<Case> sort = Comparator.comparing(Case::getDescription);
            Sort sortBy = Sort.by(Sort.Order.desc("id"));
            AtomicLong l = new AtomicLong();
            List<Case> cases = generateCases(l::getAndIncrement, sort, SECONDS, 3, 5, 7, 1, 8, 0);
            List<CaseLastTwistResponseDto> twists = sortTwists(0, 5, sort,
                    c(cases.get(0), 1577836798),//31 December 2019, 23:59:58
                    c(cases.get(1), 1577836797),//31 December 2019, 23:59:57
                    c(cases.get(3), 1577836790) //31 December 2019, 23:59:50
            );
            GeneralAccountNameRequestDto generalName = generalAccountMapper.toNameRequest(generalAccount.getName());
            GeneralAccountIdAndUuidDto generalId = generalAccountMapper.toIdDTO(generalAccount);
            Page<Case> page = new PageImpl<>(cases);
            when(caseRepository.findAll(PageRequest.of(0, 1, sortBy))).thenReturn(page);
            when(generalAccountInformationService.getId(generalName)).thenReturn(generalId);
            when(actualCaseRepository.dates(generalAccount.getId(), 0L, 5L, sortBy)).thenReturn(twists);

            l.set(0);
            CasePagePartialResponseDto result = caseActualInformationService.getPage(0, 1, generalName);
            long d0 = cases.get(0).getCooldown().minus(Duration.of(2, SECONDS)).getSeconds();
            long d1 = cases.get(1).getCooldown().minus(Duration.of(3, SECONDS)).getSeconds();
            long d3 = cases.get(3).getCooldown().minus(Duration.of(10, SECONDS)).getSeconds();
            List<CaseLightPartialResponseDto> casesEq = restructuredCases(cases, sort, SECONDS,
                    d0, d1, 0, d3, 0, 0)
                    .stream()
                    .map(caseMapper::toLightPartialDTO)
                    .toList();
            CasePagePartialResponseDto resultEq = pageMapper.toPartialDTO(casesEq, 1, 0);
            Assertions.assertEquals(resultEq, result);
        }
    }
}