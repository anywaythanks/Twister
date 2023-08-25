package com.github.anywaythanks.twisterresource.models.dto.inventory;

public class InventoryNameResponseDto implements Name {
    String name;

    protected InventoryNameResponseDto() {
    }

    public InventoryNameResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
