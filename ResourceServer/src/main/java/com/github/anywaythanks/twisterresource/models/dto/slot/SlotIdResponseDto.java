package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;

public class SlotIdResponseDto implements Quantity, ItemTypes<ItemIdResponseDto>, Id {
    ItemIdResponseDto item;
    Integer quantity;
    Long id;

    protected SlotIdResponseDto() {
    }

    public SlotIdResponseDto(Integer quantity, ItemIdResponseDto item, Long id) {
        this.quantity = quantity;
        this.item = item;
        this.id = id;
    }

    @Override
    public ItemIdResponseDto getItem() {
        return item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public Long getId() {
        return id;
    }
}
