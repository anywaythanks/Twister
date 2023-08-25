package com.github.anywaythanks.twisterresource.models.dto.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;

public class SlotTransferRequestDto implements Quantity, ItemTypes<ItemIdResponseDto> {
    Integer quantity;
    ItemIdResponseDto item;

    public SlotTransferRequestDto(Integer quantity, ItemIdResponseDto item) {
        this.quantity = quantity;
        this.item = item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public ItemIdResponseDto getItem() {
        return item;
    }
}
