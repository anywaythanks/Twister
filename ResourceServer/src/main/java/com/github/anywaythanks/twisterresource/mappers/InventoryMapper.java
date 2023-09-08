package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface InventoryMapper {

    InventoryNameResponseDto toNameDTO(InventoryName name);

    InventoryNameRequestDto toNameRequest(InventoryName name);

    InventoryName toName(InventoryNameRequestDto name);

    InventoryIdDto toIdDTO(Inventory inventory);

    InventoryDebitResponseDto toDebitDTO(Inventory inventory);

    @Mapping(source = "inventory.name.name", target = "name")
    @Mapping(source = "slots", target = "slots")
    InventoryPartialSlotsResponseDto toPartialSlotsDTO(List<SlotPartialResponseDto> slots, Inventory inventory);

    @Mapping(source = "inventory.name.name", target = "name")
    InventoryPartialResponseDto toPartialDTO(Inventory inventory);

    @Mapping(source = "id.id", target = "id")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    @Mapping(target = "generalAccount", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    Inventory toInventory(InventoryIdDto id);
}