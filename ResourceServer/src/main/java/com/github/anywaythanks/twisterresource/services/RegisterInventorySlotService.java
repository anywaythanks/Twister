package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterInventorySlotService {
    private final InventorySlotRepository inventorySlotRepository;
    private final InventoryRepository inventoryRepository;
    private final ItemRepository itemRepository;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerIfAbsent(InventoryIdResponseDto inventoryIdResponseDto, ItemIdResponseDto itemDto) {
        Optional<InventorySlot<Item>> opt = inventorySlotRepository
                .findFirstByInventoryIdAndItemId(inventoryIdResponseDto.getId(), itemDto.getId());
        if (opt.isEmpty()) {
            Inventory inventory = inventoryRepository.findById(inventoryIdResponseDto.getId())
                    .orElseThrow(NotFoundException::new);
            Item item = itemRepository.findById(itemDto.getId())
                    .orElseThrow(NotFoundException::new);
            inventory.getSlots().add(new InventorySlot<>(item, 0, inventory));
            inventoryRepository.save(inventory);
        }
    }
}
