package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class InventorySlotMergeDto implements Inventory<InventoryIdDto>, Quantity, Item<ItemIdDto> {
    @NonNull
    ItemIdDto item;
    @NonNull
    Integer quantity;
    @NonNull
    InventoryIdDto inventory;
}