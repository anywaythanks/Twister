package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseSlotCreateRequestDto implements Percentage, NameItem<ItemNameRequestDto>, Quantity {
    @NonNull
    BigDecimal percentage;
    @NonNull
    ItemNameRequestDto item;
    @NonNull
    Integer quantity;
}
