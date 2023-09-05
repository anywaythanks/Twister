package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class CaseSlotCreateRequestDto implements Percentage, NameItem<ItemNameRequestDto>, Quantity {
    @NonNull BigDecimal percentage;
    @NonNull ItemNameRequestDto item;
    @NonNull Integer quantity;
}
