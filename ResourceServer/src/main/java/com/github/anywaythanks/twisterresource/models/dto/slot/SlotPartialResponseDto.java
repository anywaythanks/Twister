package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotPartialResponseDto implements Quantity, ItemTypes<ItemPartialResponseDto> {
    @NonNull
    ItemPartialResponseDto item;
    @NonNull
    Integer quantity;
}
