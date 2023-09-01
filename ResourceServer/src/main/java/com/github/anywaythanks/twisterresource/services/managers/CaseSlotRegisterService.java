package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.CaseSlotName;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.repository.CaseSlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RegisterService
@RequiredArgsConstructor
public class CaseSlotRegisterService {
    CaseSlotRepository caseSlotRepository;
    ItemMapper itemMapper;
    CaseMapper caseMapper;

    @Transactional
    public void register(List<CaseSlotRegisterDto> slotsDto) {
        List<CaseSlot<Item>> slots = slotsDto.stream()
                .map(caseSlot -> {
                    Item item = itemMapper.toItem(caseSlot.getItem());
                    Case owner = caseMapper.toCase(caseSlot.getOwnerCase());
                    return new CaseSlot<>(item,
                            caseSlot.getQuantity(),
                            caseSlot.getPercentage(),
                            new CaseSlotName(), owner);
                })
                .toList();
        caseSlotRepository.saveAll(slots);
    }
}
