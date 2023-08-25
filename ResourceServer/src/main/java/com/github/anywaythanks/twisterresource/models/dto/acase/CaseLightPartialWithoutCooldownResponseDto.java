package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

public class CaseLightPartialWithoutCooldownResponseDto implements Name,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    MoneyPartialResponseDto price;
    String name;
    String visibleName;
    String description;

    protected CaseLightPartialWithoutCooldownResponseDto() {

    }

    public CaseLightPartialWithoutCooldownResponseDto(MoneyPartialResponseDto price,
                                                      String name, String visibleName, String description) {
        this.price = price;
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
    }

    @Override
    public MoneyPartialResponseDto getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getVisibleName() {
        return visibleName;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
