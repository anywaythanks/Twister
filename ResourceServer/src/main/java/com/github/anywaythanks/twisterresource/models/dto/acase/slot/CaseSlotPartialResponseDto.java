package com.github.anywaythanks.twisterresource.models.dto.acase.slot;

import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;

import java.math.BigDecimal;

public class CaseSlotPartialResponseDto implements Percentage, Item<ItemPartialResponseDto>, Quantity, Name {
    BigDecimal percentage;
    ItemPartialResponseDto item;
    Integer quantity;
    String name;

    public CaseSlotPartialResponseDto() {
    }

    public CaseSlotPartialResponseDto(BigDecimal percentage, ItemPartialResponseDto item, Integer quantity, String name) {
        this.percentage = percentage;
        this.item = item;
        this.quantity = quantity;
        this.name = name;
    }

    @Override
    public BigDecimal getPercentage() {
        return percentage;
    }

    @Override
    public ItemPartialResponseDto getItem() {
        return item;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public String getName() {
        return name;
    }
}
