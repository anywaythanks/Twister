package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialItemsResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialItemsWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@InformationService
@RequiredArgsConstructor
public class CaseInformationService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final PageMapper pageMapper;
    private final CaseSlotInformationService caseSlotInformationService;

    public CasePartialItemsResponseDto getPartialItems(CaseNameRequestDto caseName) {
        Case foundCase = caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
        List<CaseSlotPartialResponseDto> slots = caseSlotInformationService.getPartials(caseMapper.toCaseId(foundCase));
        return caseMapper.toPartialItemsDTO(slots, foundCase);
    }

    public CasePartialItemsWithoutCooldownResponseDto getPartialWithoutCooldown(CaseNameRequestDto caseName) {
        Case foundCase = caseRepository.findByName(caseName.getName())
                .orElseThrow(NotFoundException::new);
        List<CaseSlotPartialResponseDto> slots = caseSlotInformationService.getPartials(caseMapper.toCaseId(foundCase));
        return caseMapper.toPartialWithoutCooldownDTO(slots, foundCase);
    }

    public CaseWithoutCooldownPagePartialResponseDto getPageWithoutCooldown(Integer page, Integer size) {
        Page<Case> pageCase = caseRepository.findAll(PageRequest.of(page, size));
        return pageMapper.toPartialWithoutCooldownDTO(pageCase.stream()
                .map(caseMapper::toLightPartialWithoutCooldownDTO)
                .toList(), pageCase.getTotalPages(), page);
    }
}
