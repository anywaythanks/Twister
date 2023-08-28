package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CaseInformationService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final PageMapper pageMapper;


    public CasePartialResponseDto getPartial(CaseNameRequestDto caseName) {
        return caseMapper.toPartialDTO(caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new));
    }

    public CasePartialWithoutCooldownResponseDto getPartialWithoutCooldown(CaseNameRequestDto caseName) {
        return caseMapper.toPartialWithoutCooldownDTO(caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new));
    }

    public CaseWithoutCooldownPagePartialResponseDto getPageWithoutCooldown(Integer page, Integer size) {
        var pageCase = caseRepository.findAll(PageRequest.of(page, size));
        return pageMapper.toPartialWithoutCooldownDTO(pageCase.stream()
                .map(caseMapper::toLightPartialWithoutCooldownDTO).toList(), pageCase.getTotalPages(), page);
    }
}
