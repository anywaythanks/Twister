package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;

import java.math.BigDecimal;

public class CaseSlotCreateRequestDto implements Percentage, NameItem<ItemNameRequestDto>, Quantity {
    BigDecimal percentage;
    ItemNameRequestDto item;
    Integer quantity;

    @Override
    public BigDecimal getPercentage() {
        return percentage;
    }

    @Override
    public ItemNameRequestDto getItem() {
        return item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }
}
