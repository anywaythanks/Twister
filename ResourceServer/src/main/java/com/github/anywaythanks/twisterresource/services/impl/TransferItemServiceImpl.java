package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.ItemMapper;
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

    public void add(InventoryDTO.Request.Name name, SlotDTO.Request.Transfer slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryMapper.toId(inventoryInformationService.getDebit(name)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
                .orElseThrow(NotFoundException::new);
        inventory.getInventorySlotMap().putIfAbsent(item, new InventorySlot<>(item, 0));
        var slot = inventory.getInventorySlotMap().get(item);
        slot.addItems(item, slotTransfer.getQuantity());
    }

    public void remove(GeneralAccountDTO.Request.Name name, InventoryDTO.Request.Name nameInventory,
                       SlotDTO.Request.Transfer slotTransfer) {
        final var inventory = inventoryRepository.findById(inventoryMapper.toId(inventoryInformationService.getCredit(name, nameInventory)))
                .orElseThrow(NotFoundException::new);
        final var item = itemRepository.findById(itemMapper.toId(slotTransfer.getItem()))
                .orElseThrow(NotFoundException::new);
        inventory.getInventorySlotMap().putIfAbsent(item, new InventorySlot<>(item, 0));
        var slot = inventory.getInventorySlotMap().get(item);
        slot.removeItems(item, slotTransfer.getQuantity());
    }

    public void transfer(GeneralAccountDTO.Request.Name name,
                         InventoryDTO.Request.Name inventoryFrom, InventoryDTO.Request.Name inventoryTo,
                         SlotDTO.Request.Transfer slotTransfer) {
        remove(name, inventoryFrom, slotTransfer);
        add(inventoryTo, slotTransfer);
    }
}
