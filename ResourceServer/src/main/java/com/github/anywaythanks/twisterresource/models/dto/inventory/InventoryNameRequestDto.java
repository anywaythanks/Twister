package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.annotation.RequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@RequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class InventoryNameRequestDto implements Name {
    @NonNull String name;
}
