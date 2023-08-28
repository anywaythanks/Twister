package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotSellRequestDto implements Quantity, ItemTypes<ItemNameRequestDto> {
    @NonNull
    Integer quantity;
    @NonNull
    ItemNameRequestDto item;
}
