package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.*;

public interface SellService {
    void sell(GeneralAccountDTO.Request.Name name,
                     InventoryDTO.Request.Name nameInventory,
                     ItemDTO.Request.Name nameItem,
                     AccountDTO.Request.Number number,
                     SlotDTO.Request.Quantity quantity);
}
