package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryCreditResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotFullDto;
import com.github.anywaythanks.twisterresource.services.managers.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotPutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.BiConsumer;
import java.util.function.Function;

@Service

@RequiredArgsConstructor
public class TransferItemService {
    private final ItemMapper itemMapper;
    private final InventoryInformationService inventoryInformationService;
    private final InventorySlotPutService inventorySlotPutService;
    private final SlotMapper slotMapper;

    private void actionSlot(InventorySlotFullDto slotFull,
                            Function<Slot<?>, BiConsumer<Item, Integer>> action) {
        Item item = itemMapper.toItem(slotFull.getItem());
        InventorySlot<Item> slot = slotMapper.toInventorySlot(slotFull);
        action.apply(slot).accept(item, slotFull.getQuantity());
        inventorySlotPutService.put(slotMapper.toPut(slot));
    }

    @Transactional
    public void add(InventoryNameRequestDto name, SlotFullDto slotTransfer) {
        InventoryDebitResponseDto debit = inventoryInformationService.getDebit(name);
        actionSlot(slotMapper.toInventoryFull(debit, slotTransfer), slot -> slot::addItems);
    }

    @Transactional
    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotFullDto slotTransfer) {
        InventoryIdDto credit = inventoryInformationService.getInventoryId(name, nameInventory);
        actionSlot(slotMapper.toInventoryFull(credit, slotTransfer), slot -> slot::removeItems);
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto name,
                         InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                         SlotFullDto slotTransfer) {
        remove(name, inventoryFrom, slotTransfer);
        add(inventoryTo, slotTransfer);
    }
}
