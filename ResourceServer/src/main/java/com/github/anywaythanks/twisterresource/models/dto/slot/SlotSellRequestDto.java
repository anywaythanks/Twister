package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.RequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@RequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SlotSellRequestDto implements Quantity, Item<ItemNameRequestDto> {
    @NonNull Integer quantity;
    @NonNull ItemNameRequestDto item;
}
