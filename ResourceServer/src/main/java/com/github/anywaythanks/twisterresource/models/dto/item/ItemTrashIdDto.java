package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ItemTrashIdDto extends ItemIdDto {
    public ItemTrashIdDto(@NonNull Long id) {
        super(id);
    }
}
