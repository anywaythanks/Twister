package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;

public interface SellService {
    void sell(GeneralAccountNameRequestDto name,
              InventoryNameRequestDto nameInventory,
              ItemNameRequestDto nameItem,
              AccountNumberRequestDto number,
              SlotQuantityRequestDto quantity);
}
