package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseSlotRegisterDto implements Percentage, Item<ItemIdDto>, Quantity, Case<CaseIdDto> {
    @NonNull
    BigDecimal percentage;
    @NonNull
    ItemIdDto item;
    @NonNull
    Integer quantity;
    @NonNull
    CaseIdDto ownerCase;
}
