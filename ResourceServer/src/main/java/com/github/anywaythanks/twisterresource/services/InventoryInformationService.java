package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.*;
import com.github.anywaythanks.twisterresource.models.dto.mapper.*;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryInformationService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final ItemMapper itemMapper;
    private final SlotMapper slotMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    private final ItemInformationService itemInformationService;
    private final ItemRepository itemRepository;

    public InventoryInformationService(GeneralAccountInformationService generalAccountInformationService,
                                       ItemMapper itemMapper,
                                       SlotMapper slotMapper,
                                       GeneralAccountMapper generalAccountMapper,
                                       GeneralAccountRepository generalAccountRepository,
                                       InventoryMapper inventoryMapper,
                                       InventoryRepository inventoryRepository,
                                       ItemInformationService itemInformationService,
                                       ItemRepository itemRepository) {
        this.generalAccountInformationService = generalAccountInformationService;
        this.itemMapper = itemMapper;
        this.slotMapper = slotMapper;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryRepository = inventoryRepository;
        this.itemInformationService = itemInformationService;
        this.itemRepository = itemRepository;
    }

    private Inventory get(GeneralAccountDTO.Request.Name nameGeneral,
                          InventoryDTO.Request.Name nameInventory) {
        final var account = generalAccountRepository.findById(
                        generalAccountMapper.toId(generalAccountInformationService.getId(nameGeneral)))
                .orElseThrow(NotFoundException::new);
        var inventory = account.getInventories().get(inventoryMapper.toName(nameInventory));
        if (inventory == null) throw new NotFoundException();
        return inventory;
    }

    private Slot<?> get(GeneralAccountDTO.Request.Name nameGeneral,
                        InventoryDTO.Request.Name nameInventory, ItemDTO.Request.Name nameItem) {
        var item = itemRepository.findById(itemMapper.toId(itemInformationService.getId(nameItem)))
                .orElseThrow(NotFoundException::new);
        var slot = get(nameGeneral, nameInventory).getInventorySlotMap().get(item);
        if (slot == null) throw new NotFoundException();
        return slot;
    }

    public SlotDTO.Response.Id getSlotId(GeneralAccountDTO.Request.Name name,
                                         InventoryDTO.Request.Name nameInventory, ItemDTO.Request.Name nameItem) {
        return slotMapper.toIdsDTO(get(name, nameInventory, nameItem));
    }

    public InventoryDTO.Response.Id getId(GeneralAccountDTO.Request.Name name,
                                          InventoryDTO.Request.Name nameInventory) {
        return inventoryMapper.toIdDTO(get(name, nameInventory));
    }

    public InventoryDTO.Response.Debit getDebit(InventoryDTO.Request.Name nameInventory) {
        return inventoryMapper.toDebitDTO(inventoryRepository.findByName(inventoryMapper.toName(nameInventory))
                .orElseThrow(NotFoundException::new));
    }

    public InventoryDTO.Response.Credit getCredit(GeneralAccountDTO.Request.Name name, InventoryDTO.Request.Name nameInventory) {
        return inventoryMapper.toCreditDTO(get(name, nameInventory));
    }

    public SlotDTO.Response.Partial getSlotPartial(GeneralAccountDTO.Request.Name name,
                                                   InventoryDTO.Request.Name nameInventory, ItemDTO.Request.Name nameItem) {
        return slotMapper.toPartialDTO(get(name, nameInventory, nameItem));
    }


    public InventoryDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name nameGeneral,
                                                    InventoryDTO.Request.Name nameInventory) {
        final var account = generalAccountRepository.findById(
                        generalAccountMapper.toId(generalAccountInformationService.getId(nameGeneral)))
                .orElseThrow(NotFoundException::new);
        var inventory = account.getInventories().get(inventoryMapper.toName(nameInventory));
        if (inventory == null) throw new NotFoundException();
        return inventoryMapper.toPartialDTO(inventory);
    }

    public List<InventoryDTO.Response.Name> names(GeneralAccountDTO.Request.Name nameGeneral) {
        final var account = generalAccountRepository.findById(
                        generalAccountMapper.toId(generalAccountInformationService.getId(nameGeneral)))
                .orElseThrow(NotFoundException::new);
        return account.getInventories().keySet().stream().map(inventoryMapper::toNameDTO).toList();
    }
}
