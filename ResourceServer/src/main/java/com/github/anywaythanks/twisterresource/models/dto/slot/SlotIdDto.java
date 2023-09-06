package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.annotation.IdDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@IdDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class SlotIdDto implements Quantity, Item<ItemIdDto>, Id {
    @NonNull ItemIdDto item;
    @NonNull Integer quantity;
    @NonNull Long id;
}
