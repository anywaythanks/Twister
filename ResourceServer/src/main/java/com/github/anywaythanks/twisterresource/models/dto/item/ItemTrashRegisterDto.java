package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import lombok.NonNull;

@RegisterDto
public class ItemTrashRegisterDto extends ItemRegisterDto {
    public ItemTrashRegisterDto(@NonNull String name, @NonNull String visibleName) {
        super(name, visibleName);
    }
}
