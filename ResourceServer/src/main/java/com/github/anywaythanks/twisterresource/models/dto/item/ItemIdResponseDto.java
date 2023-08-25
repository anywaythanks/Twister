package com.github.anywaythanks.twisterresource.models.dto.item;

public class ItemIdResponseDto implements Id {
    Long id;

    protected ItemIdResponseDto() {
    }

    public ItemIdResponseDto(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }
}
