package com.github.anywaythanks.twisterresource.models.dto.item;

public class ItemTrashPartialResponseDto extends ItemPartialResponseDto {

    protected ItemTrashPartialResponseDto() {
    }

    public ItemTrashPartialResponseDto(ItemTypes type, String name, String visibleName) {
        super(type, name, visibleName);
    }
}
