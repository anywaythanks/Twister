package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;

public class ItemMoneyCreateRequestDto extends ItemCreateRequestDto implements Cost<MoneyCreateRequestDto> {
    MoneyCreateRequestDto cost;

    public ItemMoneyCreateRequestDto() {
    }

    public ItemMoneyCreateRequestDto(MoneyCreateRequestDto cost, String visibleName) {
        this.cost = cost;
        this.visibleName = visibleName;
    }

    @Override
    public MoneyCreateRequestDto getCost() {
        return cost;
    }
}
