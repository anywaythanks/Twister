package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotIdResponseDto implements Quantity, ItemTypes<ItemIdResponseDto>, Id {
    @NonNull
    ItemIdResponseDto item;
    @NonNull
    Integer quantity;
    @NonNull
    Long id;
}
