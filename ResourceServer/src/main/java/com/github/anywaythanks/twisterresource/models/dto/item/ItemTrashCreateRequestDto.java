package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemTrashCreateRequestDto extends ItemCreateRequestDto {
    public ItemTrashCreateRequestDto(@NonNull String visibleName) {
        super(visibleName);
    }
}
