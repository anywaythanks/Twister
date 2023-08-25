package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

public class ItemMoneyPartialResponseDto extends ItemPartialResponseDto implements Cost<MoneyPartialResponseDto> {
    MoneyPartialResponseDto cost;

    protected ItemMoneyPartialResponseDto() {
    }

    public ItemMoneyPartialResponseDto(ItemTypes type, MoneyPartialResponseDto cost, String name, String visibleName) {
        super(type, name, visibleName);
        this.cost = cost;
    }

    @Override
    public MoneyPartialResponseDto getCost() {
        return cost;
    }
}
