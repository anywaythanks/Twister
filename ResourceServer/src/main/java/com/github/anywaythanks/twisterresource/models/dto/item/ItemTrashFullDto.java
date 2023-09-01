package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.Instant;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemTrashFullDto extends ItemFullDto {
    public ItemTrashFullDto(@NonNull Long id, @NonNull String name, @NonNull String visibleName, @NonNull Instant createdOn, @NonNull Instant modifiedBy) {
        super(id, name, visibleName, createdOn, modifiedBy);
    }
}
