package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

import java.time.Duration;

public class CaseLightPartialResponseDto implements Name, Cooldown,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    MoneyPartialResponseDto price;
    Duration cooldown;
    String name;
    String visibleName;
    String description;

    protected CaseLightPartialResponseDto() {

    }

    public CaseLightPartialResponseDto(MoneyPartialResponseDto price, Duration cooldown,
                                       String name, String visibleName, String description) {
        this.price = price;
        this.cooldown = cooldown;
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
    }

    @Override
    public MoneyPartialResponseDto getPrice() {
        return price;
    }

    @Override
    public Duration getCooldown() {
        return cooldown;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setCooldown(Duration cooldown) {
        this.cooldown = cooldown;
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
