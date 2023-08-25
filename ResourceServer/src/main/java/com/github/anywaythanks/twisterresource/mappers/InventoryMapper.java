package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;

public interface InventoryMapper {
    InventoryNameResponseDto toNameDTO(InventoryName name);

    InventoryName toName(InventoryNameRequestDto name);

    InventoryIdResponseDto toIdDTO(Inventory inventory);

    InventoryCreditResponseDto toCreditDTO(Inventory inventory);

    InventoryDebitResponseDto toDebitDTO(Inventory inventory);

    Long toId(InventoryIdResponseDto id);

    InventoryPartialResponseDto toPartialDTO(Inventory inventory);
}
