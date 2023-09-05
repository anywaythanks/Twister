package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Getter
public class InventorySlotFullDto extends SlotFullDto implements Inventory<InventoryIdDto> {
    @NonNull InventoryIdDto inventory;

    public InventorySlotFullDto(@NonNull Long id, @NonNull Integer quantity, @NonNull ItemFullDto item, @NonNull InventoryIdDto inventory) {
        super(id, quantity, item);
        this.inventory = inventory;
    }
}
