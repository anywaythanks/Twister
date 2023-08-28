package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemTrashPartialResponseDto extends ItemPartialResponseDto {
    public ItemTrashPartialResponseDto(@NonNull ItemTypes type, @NonNull String name, @NonNull String visibleName) {
        super(type, name, visibleName);
    }
}
