package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferItemService {
    private final ItemRepository itemRepository;
    private final InventoryInformationService inventoryInformationService;
    private final InventoryRepository inventoryRepository;

    public void add(InventoryNameRequestDto name, SlotTransferRequestDto slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryInformationService.getDebit(name).getId())
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(slotTransfer.getItem().getId())
                .orElseThrow(NotFoundException::new);
        inventory.getInventorySlotMap().putIfAbsent(item, new InventorySlot<>(item, 0));
        var slot = inventory.getInventorySlotMap().get(item);
        slot.addItems(item, slotTransfer.getQuantity());
    }

    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotTransferRequestDto slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryInformationService.getCredit(name, nameInventory).getId())
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(slotTransfer.getItem().getId())
                .orElseThrow(NotFoundException::new);
        inventory.getInventorySlotMap().putIfAbsent(item, new InventorySlot<>(item, 0));
        var slot = inventory.getInventorySlotMap().get(item);
        slot.removeItems(item, slotTransfer.getQuantity());
    }

    public void transfer(GeneralAccountNameRequestDto name,
                         InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                         SlotTransferRequestDto slotTransfer) {
        remove(name, inventoryFrom, slotTransfer);
        add(inventoryTo, slotTransfer);
    }
}
