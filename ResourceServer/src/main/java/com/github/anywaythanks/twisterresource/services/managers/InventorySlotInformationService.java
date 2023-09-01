package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotFullDto;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import lombok.RequiredArgsConstructor;

@InformationService
@RequiredArgsConstructor
public class InventorySlotInformationService {
    private final InventorySlotRepository inventorySlotRepository;
    private final SlotMapper slotMapper;

    public InventorySlotFullDto getFull(InventoryIdDto inventoryId, ItemIdDto itemIdDto) {
        InventorySlot<Item> inventorySlot = inventorySlotRepository
                .findFirstByInventoryIdAndItemId(inventoryId.getId(), itemIdDto.getId())
                .orElseThrow(NotFoundException::new);
        return slotMapper.toInventoryFull(inventorySlot);
    }
}
