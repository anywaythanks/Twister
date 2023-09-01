package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseSlotRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@InformationService
@RequiredArgsConstructor
public class CaseSlotInformationService {
    private final CaseSlotRepository caseSlotRepository;
    private final SlotMapper slotMapper;

    public List<CaseSlotPartialResponseDto> getPartials(CaseIdDto caseIdDto) {
        return caseSlotRepository.findAllByOwnerCaseId(caseIdDto.getId())
                .stream()
                .map(slotMapper::toCaseSlot)
                .toList();
    }
}
