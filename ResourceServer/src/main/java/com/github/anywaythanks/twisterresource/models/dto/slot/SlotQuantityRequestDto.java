package com.github.anywaythanks.twisterresource.models.dto.slot;

public class SlotQuantityRequestDto implements Quantity {
    Integer quantity;

    protected SlotQuantityRequestDto() {
    }

    public SlotQuantityRequestDto(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }
}
