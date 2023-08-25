package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;
import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;

import java.util.List;

public interface InventoryInformationService {
    SlotDTO.Response.Id getSlotId(GeneralAccountDTO.Request.Name name,
                                  InventoryDTO.Request.Name nameInventory, ItemDTO.Request.Name nameItem);

    InventoryDTO.Response.Id getId(GeneralAccountDTO.Request.Name name,
                                   InventoryDTO.Request.Name nameInventory);

    InventoryDTO.Response.Debit getDebit(InventoryDTO.Request.Name nameInventory);

    InventoryDTO.Response.Credit getCredit(GeneralAccountDTO.Request.Name name, InventoryDTO.Request.Name nameInventory);

    SlotDTO.Response.Partial getSlotPartial(GeneralAccountDTO.Request.Name name,
                                            InventoryDTO.Request.Name nameInventory, ItemDTO.Request.Name nameItem);


    InventoryDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name nameGeneral,
                                             InventoryDTO.Request.Name nameInventory);

    List<InventoryDTO.Response.Name> names(GeneralAccountDTO.Request.Name nameGeneral);
}
