package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InventorySlotFullDto extends SlotFullDto implements Inventory<InventorySlotRegisterDto> {
    public InventorySlotFullDto(@NonNull Long id, @NonNull Integer quantity, @NonNull ItemFullDto item, @NonNull InventoryIdDto inventory) {
        super(id, quantity, item);
        this.inventory = inventory;
    }

    @NonNull
    InventoryIdDto inventory;
}
