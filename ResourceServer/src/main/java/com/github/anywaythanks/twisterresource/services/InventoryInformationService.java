package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryInformationService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final SlotMapper slotMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    private final ItemInformationService itemInformationService;
    private final ItemRepository itemRepository;

    private Inventory getInventory(GeneralAccountNameRequestDto nameGeneral,
                                   InventoryNameRequestDto nameInventory) {
        GeneralAccountIdResponseDto id = generalAccountInformationService.getId(nameGeneral);
        GeneralAccount account = generalAccountRepository.findById(id.getId())
                .orElseThrow(NotFoundException::new);
        InventoryName name = inventoryMapper.toName(nameInventory);
        Inventory inventory = account.getInventories().get(name);
        if (inventory == null) throw new NotFoundException();
        return inventory;
    }

    private Slot<?> getSlot(GeneralAccountNameRequestDto nameGeneral,
                            InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        ItemIdResponseDto itemId = itemInformationService.getId(nameItem);
        Item item = itemRepository.findById(itemId.getId())
                .orElseThrow(NotFoundException::new);
        Slot<?> slot = getInventory(nameGeneral, nameInventory).getInventorySlotMap().get(item);
        if (slot == null) throw new NotFoundException();
        return slot;
    }

    public InventoryIdResponseDto getInventoryId(GeneralAccountNameRequestDto name,
                                                 InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(name, nameInventory);
        return inventoryMapper.toIdDTO(inventory);
    }

    public InventoryCreditResponseDto getCredit(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(name, nameInventory);
        return inventoryMapper.toCreditDTO(inventory);
    }

    @Transactional(readOnly = true)
    public InventoryPartialResponseDto getInventoryPartial(GeneralAccountNameRequestDto nameGeneral,
                                                           InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(nameGeneral, nameInventory);
        return inventoryMapper.toPartialDTO(inventory);
    }

    public InventoryDebitResponseDto getDebit(InventoryNameRequestDto nameInventory) {
        Inventory inventory = inventoryRepository.findByName(inventoryMapper.toName(nameInventory))
                .orElseThrow(NotFoundException::new);
        return inventoryMapper.toDebitDTO(inventory);
    }

    public SlotIdResponseDto getSlotId(GeneralAccountNameRequestDto name,
                                       InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        Slot<?> slot = getSlot(name, nameInventory, nameItem);
        return slotMapper.toIdsDTO(slot);
    }

    public SlotPartialResponseDto getSlotPartial(GeneralAccountNameRequestDto name,
                                                 InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        Slot<?> slot = getSlot(name, nameInventory, nameItem);
        return slotMapper.toPartialDTO(slot);
    }

    public List<InventoryNameResponseDto> getInventoryNames(GeneralAccountNameRequestDto nameGeneral) {
        GeneralAccountIdResponseDto id = generalAccountInformationService.getId(nameGeneral);
        GeneralAccount account = generalAccountRepository.findById(id.getId())
                .orElseThrow(NotFoundException::new);
        return account
                .getInventories()
                .keySet()
                .stream()
                .map(inventoryMapper::toNameDTO)
                .toList();
    }
}
