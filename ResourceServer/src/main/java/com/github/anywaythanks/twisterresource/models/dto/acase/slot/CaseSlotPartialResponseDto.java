package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CaseSlotPartialResponseDto implements Percentage, Item<ItemPartialResponseDto>, Quantity, Name {
    @NonNull BigDecimal percentage;
    @NonNull ItemPartialResponseDto item;
    @NonNull Integer quantity;
    @NonNull String name;
}
