package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;

import java.util.List;

public interface InventoryInformationService {
    SlotIdResponseDto getSlotId(GeneralAccountNameRequestDto name,
                                InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem);

    InventoryIdResponseDto getId(GeneralAccountNameRequestDto name,
                                 InventoryNameRequestDto nameInventory);

    InventoryDebitResponseDto getDebit(InventoryNameRequestDto nameInventory);

    InventoryCreditResponseDto getCredit(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory);

    SlotPartialResponseDto getSlotPartial(GeneralAccountNameRequestDto name,
                                          InventoryNameRequestDto nameInventory, ItemNameRequestDto nameItem);


    InventoryPartialResponseDto getPartial(GeneralAccountNameRequestDto nameGeneral,
                                           InventoryNameRequestDto nameInventory);

    List<InventoryNameResponseDto> names(GeneralAccountNameRequestDto nameGeneral);
}
