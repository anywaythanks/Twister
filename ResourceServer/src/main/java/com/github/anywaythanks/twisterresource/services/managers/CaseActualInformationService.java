package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ActualCaseRepository;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.LongSummaryStatistics;

@InformationService
@RequiredArgsConstructor
public class CaseActualInformationService {
    private final CaseRepository caseRepository;
    private final ActualCaseRepository actualCaseRepository;
    private final CaseMapper caseMapper;
    private final PageMapper pageMapper;
    private final GeneralAccountInformationService generalAccountInformationService;
    private final CaseSlotInformationService caseSlotInformationService;
    private static final Sort.Order ORDER_DESC_CASE_ID = Sort.Order.desc("id");

    private Case getCase(CaseNameRequestDto caseName) {
        return caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
    }

    private Duration getActualCooldown(GeneralAccountNameRequestDto name,
                                       Case foundCase) {
        GeneralAccountIdAndUuidDto generalAccountId = generalAccountInformationService.getId(name);
        Long caseId = foundCase.getId();
        List<CaseLastTwistResponseDto> dates = actualCaseRepository.dates(generalAccountId.getId(), caseId, caseId, Sort.unsorted());
        return dates.isEmpty() ?
                Duration.ZERO :
                subtractDuration(dates.get(0).getLastTwist(), foundCase.getCooldown());
    }

    private Duration subtractDuration(Instant lastDate, Duration cooldown) {
        Duration duration = cooldown.minus(Duration.between(lastDate, Instant.now()));
        if (duration.isNegative()) return Duration.ZERO;
        return duration;
    }

    public CaseFullDto getFull(GeneralAccountNameRequestDto name,
                               CaseNameRequestDto caseName) {
        Case foundCase = getCase(caseName);
        Duration duration = getActualCooldown(name, foundCase);
        return caseMapper
                .toFull(foundCase)
                .withCooldown(duration);
    }

    public CaseItemsPartialResponseDto getPartialItems(GeneralAccountNameRequestDto name,
                                                       CaseNameRequestDto caseName) {
        Case foundCase = getCase(caseName);
        Duration duration = getActualCooldown(name, foundCase);
        List<CaseSlotPartialResponseDto> slots = caseSlotInformationService.getPartials(caseMapper.toCaseId(foundCase));
        return caseMapper
                .toPartialItemsDTO(slots, foundCase)
                .withCooldown(duration);
    }

    public CasePagePartialResponseDto getPage(int page, int size, GeneralAccountNameRequestDto name) {
        GeneralAccountIdAndUuidDto generalAccountId = generalAccountInformationService.getId(name);
        Page<Case> pageCase = caseRepository.findAll(PageRequest.of(page, size, Sort.by(ORDER_DESC_CASE_ID)));
        if (pageCase.isEmpty()) return pageMapper.toPartialDTO(List.of(), pageCase.getTotalPages(), page);

        LongSummaryStatistics statistic = pageCase.stream()
                .mapToLong(Case::getId)
                .summaryStatistics();
        List<CaseLastTwistResponseDto> dates = actualCaseRepository.dates(generalAccountId.getId(),
                statistic.getMin(),
                statistic.getMax(),
                Sort.by(ORDER_DESC_CASE_ID));

        Iterator<CaseLastTwistResponseDto> datesIt = dates.iterator();
        CaseLastTwistResponseDto date = datesIt.next();
        List<CaseLightPartialResponseDto> result = new ArrayList<>();
        for (Case aCase : pageCase) {
            Duration duration = Duration.ZERO;
            //Because orderBy are identical, id are located in the same places with "white" spots.
            if (date.getId().compareTo(aCase.getId()) == 0) {
                duration = subtractDuration(date.getLastTwist(), aCase.getCooldown());
                if (datesIt.hasNext()) {
                    date = datesIt.next();
                }
            }
            result.add(caseMapper
                    .toLightPartialDTO(aCase)
                    .withCooldown(duration));
        }
        return pageMapper.toPartialDTO(result, pageCase.getTotalPages(), page);
    }
}
