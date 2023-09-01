package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotFullDto implements Quantity, Item<ItemFullDto>, Id {
    @NonNull
    Long id;
    @NonNull
    Integer quantity;
    @NonNull
    ItemFullDto item;
}
