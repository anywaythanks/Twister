package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@InformationService
@RequiredArgsConstructor
public class InventoryInformationService {
    private final InventoryRepository inventoryRepository;
    private final GeneralAccountInformationService generalAccountInformationService;
    private final InventoryMapper inventoryMapper;
    private final ItemInformationService itemInformationService;
    private final InventorySlotInformationService inventorySlotInformationService;

    private Inventory getInventory(GeneralAccountNameRequestDto nameGeneral,
                                   InventoryNameRequestDto nameInventory) {
        GeneralAccountIdAndUuidDto id = generalAccountInformationService.getId(nameGeneral);
        InventoryName name = inventoryMapper.toName(nameInventory);
        return inventoryRepository.findContaining(id.getId(), name)
                .orElseThrow(NotFoundException::new);
    }


    public InventoryIdDto getInventoryId(GeneralAccountNameRequestDto name,
                                         InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(name, nameInventory);
        return inventoryMapper.toIdDTO(inventory);
    }

    @Transactional(readOnly = true)
    public InventoryPartialSlotsResponseDto getInventoryPartial(GeneralAccountNameRequestDto nameGeneral,
                                                                InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(nameGeneral, nameInventory);
        List<SlotPartialResponseDto> list = inventorySlotInformationService
                .getPartials(inventoryMapper.toIdDTO(inventory));
        return inventoryMapper.toPartialSlotsDTO(list, inventory);
    }

    public SlotPartialResponseDto getSlotPartial(GeneralAccountNameRequestDto nameGeneral,
                                                 InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem) {
        ItemIdDto itemId = itemInformationService.getId(nameItem);
        InventoryIdDto inventory = getInventoryId(nameGeneral, nameInventory);
        return inventorySlotInformationService.getPartial(inventory, itemId);
    }

    public InventoryIdDto getId(GeneralAccountNameRequestDto nameGeneral,
                                InventoryNameRequestDto nameInventory) {
        Inventory inventory = getInventory(nameGeneral, nameInventory);
        return inventoryMapper.toIdDTO(inventory);
    }

    public InventoryDebitResponseDto getDebit(InventoryNameRequestDto nameInventory) {
        Inventory inventory = inventoryRepository.findByName(inventoryMapper.toName(nameInventory))
                .orElseThrow(NotFoundException::new);
        return inventoryMapper.toDebitDTO(inventory);
    }


    public List<InventoryNameResponseDto> getInventoryNames(GeneralAccountNameRequestDto nameGeneral) {
        GeneralAccountIdAndUuidDto id = generalAccountInformationService.getId(nameGeneral);
        return inventoryRepository.findNames(id.getId())
                .stream()
                .map(inventoryMapper::toNameDTO)
                .toList();
    }
}
