package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotRegisterDto;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@RegisterService
@RequiredArgsConstructor
public class InventorySlotRegisterService {
    private final InventorySlotRepository inventorySlotRepository;
    private final ItemMapper itemMapper;
    private final InventoryMapper inventoryMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void register(InventorySlotRegisterDto inventorySlotRegisterDto) {
        Inventory inventory = inventoryMapper.toInventory(inventorySlotRegisterDto.getInventory());
        Item item = itemMapper.toItem(inventorySlotRegisterDto.getItem());
        InventorySlot<?> inventorySlot = new InventorySlot<>(item, inventorySlotRegisterDto.getQuantity(), inventory);
        inventorySlotRepository.save(inventorySlot);
    }
}
