package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.IdDto;
import lombok.NonNull;

@IdDto
public class ItemMoneyIdDto extends ItemIdDto {
    public ItemMoneyIdDto(@NonNull Long id) {
        super(id);
    }
}
