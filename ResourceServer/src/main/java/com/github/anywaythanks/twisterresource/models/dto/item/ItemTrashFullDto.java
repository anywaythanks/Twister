package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import lombok.NonNull;

import java.time.Instant;

@FullDto
public class ItemTrashFullDto extends ItemFullDto {
    public ItemTrashFullDto(@NonNull Long id, @NonNull String name, @NonNull String visibleName, @NonNull Instant createdOn, @NonNull Instant modifiedBy) {
        super(id, name, visibleName, createdOn, modifiedBy);
    }
}
