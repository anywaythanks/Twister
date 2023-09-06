package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.Dto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Dto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SlotActionDto implements Quantity, Item<ItemFullDto> {
    @NonNull Integer quantity;
    @NonNull ItemFullDto item;
}