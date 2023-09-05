package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@NoArgsConstructor(access = PROTECTED)
public class ItemTrashCreateRequestDto extends ItemCreateRequestDto {
    public ItemTrashCreateRequestDto(@NonNull String visibleName) {
        super(visibleName);
    }
}
