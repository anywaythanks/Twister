package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.PageMapper;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.services.CaseInformationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CaseInformationServiceImpl implements CaseInformationService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final PageMapper pageMapper;

    public CaseInformationServiceImpl(CaseRepository caseRepository,
                                      CaseMapper caseMapper,
                                      PageMapper pageMapper) {
        this.caseRepository = caseRepository;
        this.caseMapper = caseMapper;
        this.pageMapper = pageMapper;
    }


    public CaseDTO.Response.Partial getPartial(CaseDTO.Request.Name caseName) {
        return caseMapper.toPartialDTO(caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new));
    }

    public CaseDTO.Response.PartialWithoutCooldown getPartialWithoutCooldown(CaseDTO.Request.Name caseName) {
        return caseMapper.toPartialWithoutCooldownDTO(caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new));
    }

    public PageDTO.Response.Partial<CaseDTO.Response.LightPartialWithoutCooldown> getPageWithoutCooldown(Integer page, Integer size) {
        var pageCase = caseRepository.findAll(PageRequest.of(page, size));
        return pageMapper.toPartialDTO(pageCase.stream()
                .map(caseMapper::toLightPartialWithoutCooldownDTO).toList(), pageCase.getTotalPages(), page);
    }
}
