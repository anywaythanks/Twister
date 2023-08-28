package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotTransferRequestDto implements Quantity, ItemTypes<ItemIdResponseDto> {
    @NonNull
    Integer quantity;
    @NonNull
    ItemIdResponseDto item;
}
