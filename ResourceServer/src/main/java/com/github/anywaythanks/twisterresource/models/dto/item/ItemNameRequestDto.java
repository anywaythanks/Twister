package com.github.anywaythanks.twisterresource.models.dto.item;

public class ItemNameRequestDto implements Name {
    String name;

    protected ItemNameRequestDto() {
    }

    public ItemNameRequestDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
