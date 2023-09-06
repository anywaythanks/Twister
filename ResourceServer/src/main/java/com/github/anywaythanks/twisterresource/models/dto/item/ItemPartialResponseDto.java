package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public abstract class ItemPartialResponseDto implements Name, Type, VisibleName {
    @NonNull ItemTypes type;
    @NonNull String name;
    @NonNull String visibleName;
}
