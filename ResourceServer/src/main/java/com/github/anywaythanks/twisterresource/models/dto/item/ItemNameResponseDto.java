package com.github.anywaythanks.twisterresource.models.dto.item;

public class ItemNameResponseDto implements Name {
    String name;

    protected ItemNameResponseDto() {
    }

    public ItemNameResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
