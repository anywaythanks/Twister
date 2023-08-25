package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;

public class SlotSellRequestDto implements Quantity, ItemTypes<ItemNameRequestDto> {
    Integer quantity;
    ItemNameRequestDto item;

    public SlotSellRequestDto(Integer quantity, ItemNameRequestDto item) {
        this.quantity = quantity;
        this.item = item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public ItemNameRequestDto getItem() {
        return item;
    }
}
