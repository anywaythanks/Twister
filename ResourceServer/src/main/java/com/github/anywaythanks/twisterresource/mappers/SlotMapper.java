package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapstructConfig.class)
public interface SlotMapper {

    @Mapping(source = "slot.item", target = "item")
    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotPartialResponseDto toPartialDTO(Slot<Item> slot);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotIdDto toIdsDTO(Slot<Item> slot);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    SlotFullDto toFull(Slot<Item> slot);

    @Mapping(source = "quantity.quantity", target = "quantity")
    SlotFullDto toFull(SlotQuantityRequestDto quantity, SlotIdDto slot);

    InventorySlotPutDto toPut(InventorySlotActionDto inventorySlot);

    InventorySlotRegisterDto toInventoryRegister(InventorySlotPutDto inventorySlotPutDto);

    @Mapping(source = "item", target = "item")
    CaseSlotRegisterDto toCaseRegister(ItemIdDto item, CaseSlotCreateRequestDto create);

    @Mapping(source = "inventorySlot.quantityItem", target = "quantity")
    @Mapping(source = "inventorySlot.inventory", target = "inventory", resultType = InventoryIdDto.class)
    InventorySlotFullDto toInventoryFull(InventorySlot<Item> inventorySlot);
    @Mapping(target = "withQuantity", ignore = true)
    InventorySlotActionDto toInventoryAction(InventoryIdDto inventory, SlotActionDto actionDto);

    @Mapping(source = "quantity.quantity", target = "quantity")
    @Mapping(target = "withQuantity", ignore = true)
    SlotActionDto toAction(ItemFullDto item, SlotQuantityRequestDto quantity);

    @Mapping(source = "slot.quantityItem", target = "quantity")
    @Mapping(target = "withQuantity", ignore = true)
    SlotActionDto toAction(Slot<Item> slot);

    @Mapping(source = "inventorySlotFull.quantity", target = "quantityItem")
    InventorySlot<Item> toInventorySlot(InventorySlotFullDto inventorySlotFull);

    @Mapping(source = "slotAction.quantity", target = "quantityItem")
    @Mapping(target = "id", ignore = true)
    InventorySlot<Item> toInventorySlot(InventorySlotActionDto slotAction);

    @Mapping(source = "caseSlot.name.name", target = "name")
    @Mapping(source = "caseSlot.percentageWining", target = "percentage")
    @Mapping(source = "caseSlot.quantityItem", target = "quantity")
    CaseSlotPartialResponseDto toCaseSlotPartial(CaseSlot<Item> caseSlot);

    @InheritInverseConfiguration
    @Mapping(source = "caseSlot.name.name", target = "name")
    CaseSlotFullDto toCaseSlotFull(CaseSlot<Item> caseSlot);

    @Mapping(source = "fullDto.percentage", target = "percentageWining")
    @Mapping(source = "fullDto.quantity", target = "quantityItem")
    CaseSlot<Item> toCaseSlot(CaseSlotFullDto fullDto);

    CaseSlotName toName(String name);
}