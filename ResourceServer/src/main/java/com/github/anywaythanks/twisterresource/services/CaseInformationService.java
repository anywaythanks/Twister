package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CaseInformationService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final PageMapper pageMapper;

    @Transactional(readOnly = true)
    public CasePartialResponseDto getPartial(CaseNameRequestDto caseName) {
        Case foundCase = caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
        return caseMapper.toPartialDTO(foundCase);
    }
    @Transactional(readOnly = true)
    public CasePartialWithoutCooldownResponseDto getPartialWithoutCooldown(CaseNameRequestDto caseName) {
        Case foundCase = caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
        return caseMapper.toPartialWithoutCooldownDTO(foundCase);
    }

    public CaseWithoutCooldownPagePartialResponseDto getPageWithoutCooldown(Integer page, Integer size) {
        Page<Case> pageCase = caseRepository.findAll(PageRequest.of(page, size));
        return pageMapper.toPartialWithoutCooldownDTO(pageCase.stream()
                .map(caseMapper::toLightPartialWithoutCooldownDTO)
                .toList(), pageCase.getTotalPages(), page);
    }
}
