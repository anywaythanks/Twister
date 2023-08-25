package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;

public interface TransferItemService {
    void add(InventoryDTO.Request.Name name, SlotDTO.Request.Transfer slotTransfer);

    void remove(GeneralAccountDTO.Request.Name name, InventoryDTO.Request.Name nameInventory,
                SlotDTO.Request.Transfer slotTransfer);

    void transfer(GeneralAccountDTO.Request.Name name,
                  InventoryDTO.Request.Name inventoryFrom, InventoryDTO.Request.Name inventoryTo,
                  SlotDTO.Request.Transfer slotTransfer);
}
