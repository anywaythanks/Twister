package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(uses = SlotMapper.class, componentModel = "spring")
public interface InventoryMapper {

    InventoryNameResponseDto toNameDTO(InventoryName name);

    InventoryName toName(InventoryNameRequestDto name);

    InventoryIdResponseDto toIdDTO(Inventory inventory);

    InventoryCreditResponseDto toCreditDTO(Inventory inventory);

    InventoryDebitResponseDto toDebitDTO(Inventory inventory);

    @Mappings({
            @Mapping(source = "inventory.name.name", target = "name"),
            @Mapping(source = "inventory.inventorySlotMap", target = "slots")
    })
    InventoryPartialResponseDto toPartialDTO(Inventory inventory);
}