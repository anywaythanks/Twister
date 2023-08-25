package com.github.anywaythanks.twisterresource.models.dto.inventory;

public class InventoryIdResponseDto implements Id {
    Long id;

    protected InventoryIdResponseDto() {
    }

    public InventoryIdResponseDto(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
