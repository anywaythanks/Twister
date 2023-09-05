package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class CaseSlotRegisterDto implements Percentage, Item<ItemIdDto>, Quantity {
    @NonNull BigDecimal percentage;
    @NonNull ItemIdDto item;
    @NonNull Integer quantity;
}
