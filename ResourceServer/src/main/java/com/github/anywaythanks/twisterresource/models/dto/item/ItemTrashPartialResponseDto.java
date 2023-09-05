package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import lombok.NonNull;

@PartialResponseDto
public class ItemTrashPartialResponseDto extends ItemPartialResponseDto {
    public ItemTrashPartialResponseDto(@NonNull ItemTypes type, @NonNull String name, @NonNull String visibleName) {
        super(type, name, visibleName);
    }
}
