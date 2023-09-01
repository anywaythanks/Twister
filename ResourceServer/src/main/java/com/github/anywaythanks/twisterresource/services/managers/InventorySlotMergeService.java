package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotMergeDto;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import lombok.RequiredArgsConstructor;

@MergeService
@RequiredArgsConstructor
public class InventorySlotMergeService {
    private final InventorySlotRepository inventorySlotRepository;
    private final ItemMapper itemMapper;
    private final InventoryMapper inventoryMapper;
    private final SlotMapper slotMapper;

    public void merge(InventorySlotMergeDto mergeDto) {
        InventorySlot<?> slot = slotMapper.toInventorySlot(mergeDto);
        inventorySlotRepository.save(slot);
    }
}
