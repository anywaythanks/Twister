package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(config = MapstructConfig.class)
public interface SlotMapper {

    @Mapping(source = "slot.item", target = "item")
    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotPartialResponseDto toPartialDTO(Slot<Item> slot);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotIdResponseDto toIdsDTO(Slot<Item> slot);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotFullDto toTransfer(Slot<Item> slot);

    @Mapping(source = "quantity.quantity", target = "quantity")
    SlotFullDto toTransfer(SlotQuantityRequestDto quantity, SlotIdResponseDto slot);

    @Mapping(source = "inventorySlot.quantityItem", target = "quantity")
    @Mapping(source = "inventorySlot.inventory", target = "inventory", resultType = InventoryIdDto.class)
    InventorySlotPutDto toPut(InventorySlot<Item> inventorySlot);

    InventorySlotRegisterDto toRegister(InventorySlotPutDto inventorySlotPutDto);

    InventorySlotMergeDto toMerge(InventorySlotPutDto inventorySlotPutDto);

    @Mapping(source = "inventorySlot.quantityItem", target = "quantity")
    @Mapping(source = "inventorySlot.inventory", target = "inventory", resultType = InventoryIdDto.class)
    InventorySlotFullDto toInventoryFull(InventorySlot<Item> inventorySlot);

    @Mapping(source = "slotFull.quantity", target = "quantity")
    @Mapping(source = "slotFull.item", target = "item")
    @Mapping(source = "slotFull.id", target = "id")
    @Mapping(source = "inventoryId", target = "inventory")
    InventorySlotFullDto toInventoryFull(InventoryIdDto inventoryId, SlotFullDto slotFull);

    @Mapping(source = "inventorySlotFull.quantity", target = "quantityItem")
    InventorySlot<Item> toInventorySlot(InventorySlotFullDto inventorySlotFull);

    @Mapping(source = "inventorySlotFull.quantity", target = "quantityItem")
    InventorySlot<Item> toInventorySlot(InventorySlotMergeDto inventorySlotFull);

    default List<SlotPartialResponseDto> toPartialsDto(Set<InventorySlot<Item>> set) {
        return set.stream().map(this::toPartialDTO).toList();
    }

    CaseSlotPartialResponseDto toCaseSlot(CaseSlot<Item> caseSlot);
}