package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;

public class SlotPartialResponseDto implements Quantity, ItemTypes<ItemPartialResponseDto> {
    ItemPartialResponseDto item;
    Integer quantity;

    protected SlotPartialResponseDto() {
    }

    public SlotPartialResponseDto(Integer quantity, ItemPartialResponseDto item) {
        this.quantity = quantity;
        this.item = item;
    }

    @Override
    public ItemPartialResponseDto getItem() {
        return item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }
}
