package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.CaseSlotName;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.repository.CaseSlotSaveAllRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RegisterService
@RequiredArgsConstructor
public class CaseSlotRegisterService {
    private final CaseSlotSaveAllRepository caseSlotSaveAllRepository;
    private final ItemMapper itemMapper;
    private final CaseMapper caseMapper;

    @Transactional
    public void register(CaseIdDto caseOwnerId, List<CaseSlotRegisterDto> slotsDto) {
        Case owner = caseMapper.toCase(caseOwnerId);
        List<CaseSlot<Item>> slots = slotsDto.stream()
                .map(caseSlot -> {
                    Item item = itemMapper.toItem(caseSlot.getItem());
                    return (CaseSlot<Item>) CaseSlot.builder()
                            .item(item)
                            .quantityItem(caseSlot.getQuantity())
                            .winRate(caseSlot.getWinRate())
                            .name(CaseSlotName.builder().build())
                            .ownerCase(owner)
                            .build();
                })
                .toList();
        caseSlotSaveAllRepository.saveAll(owner, slots);
    }
}
