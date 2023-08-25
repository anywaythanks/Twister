package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.services.TransferItemService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransferItemServiceImpl implements TransferItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final InventoryInformationServiceImpl inventoryInformationService;
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public TransferItemServiceImpl(ItemRepository itemRepository,
                                   ItemMapper itemMapper,
                                   InventoryInformationServiceImpl inventoryInformationService,
                                   InventoryRepository inventoryRepository,
                                   InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
        this.inventoryInformationService = inventoryInformationService;
        this.inventoryRepository = inventoryRepository;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public void add(InventoryNameRequestDto name, SlotTransferRequestDto slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryMapper.toId(inventoryInformationService.getDebit(name)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
                .orElseThrow(NotFoundException::new);
        inventory.getInventorySlotMap().putIfAbsent(item, new InventorySlot<>(item, 0));
        var slot = inventory.getInventorySlotMap().get(item);
        slot.addItems(item, slotTransfer.getQuantity());
    }

    public void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                       SlotTransferRequestDto slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryMapper.toId(inventoryInformationService.getCredit(name, nameInventory)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
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
