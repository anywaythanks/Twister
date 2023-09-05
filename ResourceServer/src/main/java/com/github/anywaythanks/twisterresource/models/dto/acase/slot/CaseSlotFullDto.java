package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class CaseSlotFullDto implements Percentage, Item<ItemFullDto>, Quantity, Name, Id, Case<CaseIdDto> {
    @NonNull Long id;
    @NonNull BigDecimal percentage;
    @NonNull ItemFullDto item;
    @NonNull Integer quantity;
    @NonNull String name;
    @NonNull CaseIdDto ownerCase;
}
