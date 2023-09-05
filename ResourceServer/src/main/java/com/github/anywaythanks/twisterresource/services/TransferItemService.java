package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotActionDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotActionDto;
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

    private void actionSlot(InventorySlotActionDto slotAction,
                            Function<Slot<?>, BiConsumer<Item, Integer>> action) {
        Item item = itemMapper.toItem(slotAction.getItem());
        InventorySlot<Item> slot = slotMapper.toInventorySlot(slotAction);
        action.apply(slot).accept(item, slotAction.getQuantity());
        inventorySlotPutService.put(slotMapper.toPut(slot));
    }

    @Transactional
    public void add(InventoryNameRequestDto name, SlotActionDto actionDto) {
        InventoryDebitResponseDto debit = inventoryInformationService.getDebit(name);
        actionSlot(slotMapper.toInventoryAction(debit, actionDto), slot -> slot::addItems);
    }

    @Transactional
    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotActionDto actionDto) {
        InventoryIdDto credit = inventoryInformationService.getInventoryId(name, nameInventory);
        actionSlot(slotMapper.toInventoryAction(credit, actionDto), slot -> slot::removeItems);
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto name,
                         InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                         SlotActionDto actionDto) {
        remove(name, inventoryFrom, actionDto);
        add(inventoryTo, actionDto);
    }
}
