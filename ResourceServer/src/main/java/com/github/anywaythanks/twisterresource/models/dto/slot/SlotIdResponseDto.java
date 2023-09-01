package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotIdResponseDto implements Quantity, Item<ItemIdDto>, Id {
    @NonNull
    ItemIdDto item;
    @NonNull
    Integer quantity;
    @NonNull
    Long id;
}
