package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.TwistMark;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ActualCaseRepository;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.TwistMarkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CaseActualInformationService {
    private final ActualCaseRepository actualCaseRepository;
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final PageMapper pageMapper;
    private final GeneralAccountInformationService generalAccountInformationService;
    private final TwistMarkRepository twistMarkRepository;
    private final RegisterTwistMarkService registerTwistMarkService;
    private static final Sort.Order ORDER_DESC_CASE_ID = Sort.Order.desc("id");

    private Case getCase(CaseNameRequestDto caseName) {
        return caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
    }

    private Duration getActualCooldown(GeneralAccountNameRequestDto name,
                                       Case foundCase) {
        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalAccountId.getId())
                .orElseThrow(NotFoundException::new);
        Long caseId = foundCase.getId();
        List<CaseLastTwistResponseDto> dates = actualCaseRepository.dates(generalAccount, caseId, caseId, Sort.unsorted());
        return dates.isEmpty() ?
                Duration.ZERO :
                subtractDuration(dates.get(0).getLastTwist(), foundCase.getCooldown());
    }

    private Duration getAndUpdateActualCooldown(GeneralAccountNameRequestDto name,
                                                Case foundCase) {
        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalAccountId.getId())
                .orElseThrow(NotFoundException::new);
        Instant now = Instant.now();
        registerTwistMarkService.registerIfAbsent(new TwistMark(foundCase, generalAccount, false, now, now));
        Optional<TwistMark> optionalTwistMark = twistMarkRepository.findFirstByGeneralAccountAndTwistCase(generalAccount, foundCase);
        TwistMark twistMark = optionalTwistMark.orElseThrow(NotFoundException::new);
        Duration actualDuration = !twistMark.getConsider() ?
                Duration.ZERO :
                subtractDuration(optionalTwistMark.get().getUpdatedOn(), foundCase.getCooldown());
        twistMark.setUpdatedOn(Instant.now());
        twistMark.setConsider(true);
        twistMarkRepository.saveAndFlush(twistMark);
        return actualDuration;
    }

    private Duration subtractDuration(Instant lastDate, Duration cooldown) {
        Duration duration = cooldown.minus(Duration.between(lastDate, Instant.now()));
        if (duration.isNegative()) return Duration.ZERO;
        return duration;
    }

    @Transactional
    public CaseCooldownIdResponseDto getAndUpdateCooldownId(GeneralAccountNameRequestDto name,
                                                            CaseNameRequestDto caseName) {
        Case foundCase = getCase(caseName);
        Duration duration = getAndUpdateActualCooldown(name, foundCase);
        return caseMapper
                .toCooldownIdDto(foundCase)
                .setCooldown(duration);
    }

    @Transactional(readOnly = true)
    public CasePartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                             CaseNameRequestDto caseName) {
        Case foundCase = getCase(caseName);
        Duration duration = getActualCooldown(name, foundCase);
        return caseMapper
                .toPartialDTO(foundCase)
                .setCooldown(duration);
    }

    public CasePagePartialResponseDto getPage(int page, int size, GeneralAccountNameRequestDto name) {
        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalAccountId.getId())
                .orElseThrow(NotFoundException::new);
        Page<Case> pageCase = caseRepository.findAll(PageRequest.of(page, size, Sort.by(ORDER_DESC_CASE_ID)));
        if (pageCase.isEmpty()) return pageMapper.toPartialDTO(List.of(), pageCase.getTotalPages(), page);

        LongSummaryStatistics statistic = pageCase.stream()
                .mapToLong(Case::getId)
                .summaryStatistics();
        List<CaseLastTwistResponseDto> dates = actualCaseRepository.dates(generalAccount,
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
                    .setCooldown(duration));
        }
        return pageMapper.toPartialDTO(result, pageCase.getTotalPages(), page);
    }
}
