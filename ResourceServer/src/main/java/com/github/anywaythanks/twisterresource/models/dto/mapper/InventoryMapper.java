package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;

public interface InventoryMapper {
    InventoryDTO.Response.Name toNameDTO(InventoryName name);

    InventoryName toName(InventoryDTO.Request.Name name);

    InventoryDTO.Response.Id toIdDTO(Inventory inventory);

    InventoryDTO.Response.Credit toCreditDTO(Inventory inventory);

    InventoryDTO.Response.Debit toDebitDTO(Inventory inventory);

    Long toId(InventoryDTO.Response.Id id);

    InventoryDTO.Response.Partial toPartialDTO(Inventory inventory);
}
