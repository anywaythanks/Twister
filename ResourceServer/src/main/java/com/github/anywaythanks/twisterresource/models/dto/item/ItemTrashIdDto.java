package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.IdDto;
import lombok.NonNull;

@IdDto
public class ItemTrashIdDto extends ItemIdDto {
    public ItemTrashIdDto(@NonNull Long id) {
        super(id);
    }
}
