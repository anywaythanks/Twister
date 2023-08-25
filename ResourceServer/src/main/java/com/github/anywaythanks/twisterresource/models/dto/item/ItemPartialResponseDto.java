package com.github.anywaythanks.twisterresource.models.dto.item;

public abstract class ItemPartialResponseDto implements Name, Type, VisibleName {
    ItemTypes type;
    String name;
    String visibleName;

    protected ItemPartialResponseDto() {
    }

    protected ItemPartialResponseDto(ItemTypes type, String name, String visibleName) {
        this.type = type;
        this.name = name;
        this.visibleName = visibleName;
    }

    @Override
    public ItemTypes getType() {
        return type;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVisibleName() {
        return visibleName;
    }
}
