package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryCreditResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.InventorySlotRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Service

@RequiredArgsConstructor
public class TransferItemService {
    private final ItemRepository itemRepository;
    private final InventoryInformationService inventoryInformationService;
    private final InventoryRepository inventoryRepository;
    private final RegisterInventorySlotService registerInventorySlotService;
    private final InventorySlotRepository inventorySlotRepository;

    private void actionSlot(InventoryIdResponseDto inventoryIdResponseDto, SlotTransferRequestDto slotTransfer,
                            Function<Slot<?>, BiConsumer<Item, Integer>> action) {
        registerInventorySlotService.registerIfAbsent(inventoryIdResponseDto, slotTransfer.getItem());
        Item item = itemRepository.findById(slotTransfer.getItem().getId())
                .orElseThrow(NotFoundException::new);
        Inventory inventory = inventoryRepository.findById(inventoryIdResponseDto.getId())
                .orElseThrow(NotFoundException::new);
        InventorySlot<?> slot = inventorySlotRepository.findFirstByInventoryAndItem(inventory, item)
                .orElseThrow(NotFoundException::new);
        action.apply(slot).accept(item, slotTransfer.getQuantity());
        inventory.setModifiedBy(Instant.now());
    }

    @Transactional
    public void add(InventoryNameRequestDto name, SlotTransferRequestDto slotTransfer) {
        InventoryDebitResponseDto debit = inventoryInformationService.getDebit(name);
        actionSlot(debit, slotTransfer, slot -> slot::addItems);
    }

    @Transactional
    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotTransferRequestDto slotTransfer) {
        InventoryCreditResponseDto credit = inventoryInformationService.getCredit(name, nameInventory);
        actionSlot(credit, slotTransfer, slot -> slot::removeItems);
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto name,
                         InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                         SlotTransferRequestDto slotTransfer) {
        remove(name, inventoryFrom, slotTransfer);
        add(inventoryTo, slotTransfer);
    }
}
