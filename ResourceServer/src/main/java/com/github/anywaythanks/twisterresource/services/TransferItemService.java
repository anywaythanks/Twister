package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;

public interface TransferItemService {
    void add(InventoryNameRequestDto name, SlotTransferRequestDto slotTransfer);

    void remove(GeneralAccountNameRequestDto name, InventoryNameRequestDto nameInventory,
                SlotTransferRequestDto slotTransfer);

    void transfer(GeneralAccountNameRequestDto name,
                  InventoryNameRequestDto inventoryFrom, InventoryNameRequestDto inventoryTo,
                  SlotTransferRequestDto slotTransfer);
}
