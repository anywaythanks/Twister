package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
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
@Transactional
@RequiredArgsConstructor
public class InventoryInformationService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final SlotMapper slotMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryRepository inventoryRepository;
    private final ItemInformationService itemInformationService;
    private final ItemRepository itemRepository;

    private Inventory get(GeneralAccountNameRequestDto nameGeneral,
                          InventoryNameRequestDto nameInventory) {
        final var account = generalAccountRepository.findById(generalAccountInformationService.getId(nameGeneral).getId())
                .orElseThrow(NotFoundException::new);
        var inventory = account.getInventories().get(inventoryMapper.toName(nameInventory));
        if (inventory == null) throw new NotFoundException();
        return inventory;
    }

    private Slot<?> get(GeneralAccountNameRequestDto nameGeneral,
                        InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        var item = itemRepository.findById(itemInformationService.getId(nameItem).getId())
                .orElseThrow(NotFoundException::new);
        var slot = get(nameGeneral, nameInventory).getInventorySlotMap().get(item);
        if (slot == null) throw new NotFoundException();
        return slot;
    }

    public SlotIdResponseDto getSlotId(GeneralAccountNameRequestDto name,
                                       InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        return slotMapper.toIdsDTO(get(name, nameInventory, nameItem));
    }

    public InventoryIdResponseDto getId(GeneralAccountNameRequestDto name,
                                        InventoryNameRequestDto nameInventory) {
        return inventoryMapper.toIdDTO(get(name, nameInventory));
    }

    public InventoryDebitResponseDto getDebit(InventoryNameRequestDto nameInventory) {
        return inventoryMapper.toDebitDTO(inventoryRepository.findByName(inventoryMapper.toName(nameInventory))
                .orElseThrow(NotFoundException::new));
    }

    public InventoryCreditResponseDto getCredit(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory) {
        return inventoryMapper.toCreditDTO(get(name, nameInventory));
    }

    public SlotPartialResponseDto getSlotPartial(GeneralAccountNameRequestDto name,
                                                 InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        return slotMapper.toPartialDTO(get(name, nameInventory, nameItem));
    }


    public InventoryPartialResponseDto getPartial(GeneralAccountNameRequestDto nameGeneral,
                                                  InventoryNameRequestDto nameInventory) {
        final var account = generalAccountRepository.findById(generalAccountInformationService.getId(nameGeneral).getId())
                .orElseThrow(NotFoundException::new);
        var inventory = account.getInventories().get(inventoryMapper.toName(nameInventory));
        if (inventory == null) throw new NotFoundException();
        return inventoryMapper.toPartialDTO(inventory);
    }

    public List<InventoryNameResponseDto> names(GeneralAccountNameRequestDto nameGeneral) {
        final var account = generalAccountRepository.findById(generalAccountInformationService.getId(nameGeneral).getId())
                .orElseThrow(NotFoundException::new);
        return account.getInventories().keySet().stream().map(inventoryMapper::toNameDTO).toList();
    }
}
