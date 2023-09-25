package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotEnoughItemsException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotActionDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotActionDto;
import com.github.anywaythanks.twisterresource.services.managers.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotMergeService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotPutService;
import com.github.anywaythanks.twisterresource.services.utils.SlotUtils;
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
    private final InventorySlotInformationService inventorySlotInformationService;
    private final InventorySlotMergeService inventorySlotMergeService;
    private final SlotUtils slotUtils;

    private void actionSlot(InventorySlotActionDto slotAction,
                            Function<Slot<?>, BiConsumer<Item, Integer>> action) {
        Item item = itemMapper.toItem(slotAction.getItem());
        ItemIdDto itemId = itemMapper.toIdDTO(item);
        inventorySlotPutService.putIfAbsent(slotMapper.toPut(slotAction.withQuantity(0)));
        InventorySlotFullDto slotDto = inventorySlotInformationService.getFull(slotAction.getInventory(), itemId);
        InventorySlot<Item> slot = slotMapper.toInventorySlot(slotDto);
        action.apply(slot).accept(item, slotAction.getQuantity());
        if (slot.getQuantityItem() < 0)
            throw new NotEnoughItemsException();
        inventorySlotMergeService.merge(slotMapper.toInventoryFull(slot));
    }

    @Transactional
    public void add(InventoryNameRequestDto name, SlotActionDto actionDto) {
        InventoryDebitResponseDto debit = inventoryInformationService.getDebit(name);
        actionSlot(slotMapper.toInventoryAction(debit, actionDto), slot -> (i, q) -> slotUtils.addItems(slot, i, q));
    }

    @Transactional
    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotActionDto actionDto) {
        InventoryIdDto credit = inventoryInformationService.getInventoryId(name, nameInventory);
        actionSlot(slotMapper.toInventoryAction(credit, actionDto), slot -> (i, q) -> slotUtils.removeItems(slot, i, q));
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto name,
                         InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                         SlotActionDto actionDto) {
        remove(name, inventoryFrom, actionDto);
        add(inventoryTo, actionDto);
    }
}
