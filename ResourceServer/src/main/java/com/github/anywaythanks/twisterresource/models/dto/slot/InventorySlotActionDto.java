package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.Dto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Dto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class InventorySlotActionDto implements Quantity, Item<ItemFullDto>, Inventory<InventoryIdDto> {
    @With
    @NonNull Integer quantity;
    @NonNull ItemFullDto item;
    @NonNull InventoryIdDto inventory;
}
