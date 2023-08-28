package com.github.anywaythanks.twisterresource.models.dto.slot;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class SlotQuantityRequestDto implements Quantity {
    @NonNull
    Integer quantity;
}
