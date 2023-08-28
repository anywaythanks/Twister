package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import lombok.*;

import java.math.BigDecimal;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseSlotPartialResponseDto implements Percentage, Item<ItemPartialResponseDto>, Quantity, Name {
    @NonNull
    BigDecimal percentage;
    @NonNull
    ItemPartialResponseDto item;
    @NonNull
    Integer quantity;
    @NonNull
    String name;
}
