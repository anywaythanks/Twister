package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class InventoryPartialResponseDto implements Name {
    @NonNull String name;
}
