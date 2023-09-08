package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotPutDto;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventorySlotPutService {
    private final InventorySlotRepository inventorySlotRepository;
    private final InventorySlotRegisterService inventorySlotRegisterService;
    private final InventorySlotMergeService inventorySlotMergeService;
    private final SlotMapper slotMapper;

    public void putIfAbsent(InventorySlotPutDto inventorySlotPutDto) {
        Optional<InventorySlot<Item>> optionalInventorySlot = inventorySlotRepository.findFirstByInventoryIdAndItemId(
                inventorySlotPutDto.getInventory().getId(), inventorySlotPutDto.getItem().getId());
        if (optionalInventorySlot.isEmpty()) {
            inventorySlotRegisterService.register(slotMapper.toInventoryRegister(inventorySlotPutDto));
        }
    }
}
