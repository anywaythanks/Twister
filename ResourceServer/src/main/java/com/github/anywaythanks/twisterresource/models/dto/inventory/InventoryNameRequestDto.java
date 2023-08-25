package com.github.anywaythanks.twisterresource.models.dto.inventory;

public class InventoryNameRequestDto implements Name {
    String name;

    protected InventoryNameRequestDto() {
    }

    public InventoryNameRequestDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
