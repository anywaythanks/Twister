package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCooldownIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.ActualCaseRepository;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.services.CaseActualInformationService;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CaseActualInformationServiceImpl implements CaseActualInformationService {
    private final ActualCaseRepository hibernateCaseRepository;
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final PageMapper pageMapper;
    private final GeneralAccountInformationService generalAccountInformationService;

    public CaseCooldownIdResponseDto getCooldownId(GeneralAccountNameRequestDto name,
                                                   CaseNameRequestDto caseName) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pCase = caseMapper.toCooldownIdDto(caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new));
        var date = hibernateCaseRepository.dates(generalAccount, pCase.getId(), pCase.getId());
        Duration duration;
        if (date.isEmpty()) {
            duration = Duration.ZERO;
        } else {
            duration = sub(date.get(0).getValue(), pCase.getCooldown());
        }
        pCase.setCooldown(duration);
        return pCase;
    }

    public CasePartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                             CaseNameRequestDto caseName) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var fCase = caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
        var id = fCase.getId();
        var date = hibernateCaseRepository.dates(generalAccount, id, id);
        Duration duration;
        if (date.isEmpty()) {
            duration = Duration.ZERO;
        } else {
            duration = sub(date.get(0).getValue(), fCase.getCooldown());
        }
        var rCase = caseMapper.toPartialDTO(fCase);
        rCase.setCooldown(duration);
        return rCase;
    }

    public CasePagePartialResponseDto getPage(int page, int size, GeneralAccountNameRequestDto name) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pageCase = caseRepository.findAll(PageRequest.of(page, size));
        if (pageCase.isEmpty()) return pageMapper.toPartialDTO(List.of(), pageCase.getTotalPages(), page);
        var statistic = pageCase.stream().mapToLong(Case::getId).summaryStatistics();
        var listDate = hibernateCaseRepository.dates(generalAccount,
                statistic.getMin(), statistic.getMax());
        return pageMapper.toPartialDTO(pageCase.stream().map(aCase -> {
            var lightCase = caseMapper.toLightPartialDTO(aCase);
            var newCooldown = listDate.stream()
                    .filter(date -> date.getKey().compareTo(aCase.getId()) == 0)
                    .map(date -> sub(date.getValue(), lightCase.getCooldown()))
                    .findFirst().orElse(Duration.ZERO);//TODO:O(n^2)
            lightCase.setCooldown(newCooldown);
            return lightCase;
        }).toList(), pageCase.getTotalPages(), page);
    }

    private Duration sub(Instant lastDate, Duration cooldown) {
        var r = cooldown.minus(Duration.between(lastDate, Instant.now()));
        if (r.isNegative()) return Duration.ZERO;
        return r;
    }
}
