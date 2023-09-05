package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.MappingDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@MappingDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class InventorySlotPutDto implements Inventory<InventoryIdDto>, Quantity, Item<ItemIdDto> {
    @NonNull ItemIdDto item;
    @NonNull Integer quantity;
    @NonNull InventoryIdDto inventory;
}