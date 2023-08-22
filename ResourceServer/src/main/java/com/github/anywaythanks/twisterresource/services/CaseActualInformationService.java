package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.PageMapper;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountNameRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.HibernateCaseRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
@Transactional
public class CaseActualInformationService {
    private final HibernateCaseRepository hibernateCaseRepository;
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final PageMapper pageMapper;
    private final GeneralAccountInformationService generalAccountInformationService;

    public CaseActualInformationService(HibernateCaseRepository hibernateCaseRepository,
                                        CaseRepository caseRepository,
                                        CaseMapper caseMapper, GeneralAccountMapper generalAccountMapper,
                                        GeneralAccountRepository generalAccountRepository,
                                        PageMapper pageMapper,
                                        GeneralAccountInformationService generalAccountInformationService,
                                        GeneralAccountNameRepository generalAccountNameRepository) {
        this.hibernateCaseRepository = hibernateCaseRepository;
        this.caseRepository = caseRepository;
        this.caseMapper = caseMapper;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.pageMapper = pageMapper;
        this.generalAccountInformationService = generalAccountInformationService;
    }

    public CaseDTO.Response.CooldownId getCooldownId(GeneralAccountDTO.Request.Name name,
                                                     CaseDTO.Request.Name caseName) {
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

    public CaseDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name,
                                               CaseDTO.Request.Name caseName) {
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

    public PageDTO.Response.Partial<CaseDTO.Response.LightPartial> getPage(int page, int size,
                                                                           GeneralAccountDTO.Request.Name name) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pageCase = caseRepository.findAll(PageRequest.of(page, size));
        if (pageCase.isEmpty()) return pageMapper.toDTO(List.of(), pageCase.getTotalPages(), page);
        var statistic = pageCase.stream().mapToLong(Case::getId).summaryStatistics();
        var listDate = hibernateCaseRepository.dates(generalAccount,
                statistic.getMin(), statistic.getMax());
        return pageMapper.toDTO(pageCase.stream().map(aCase -> {
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
