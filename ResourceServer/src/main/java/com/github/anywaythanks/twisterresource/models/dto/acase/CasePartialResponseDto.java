package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

import java.time.Duration;
import java.util.List;

public class CasePartialResponseDto implements Name, Cooldown, Items<CaseSlotPartialResponseDto>,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    List<CaseSlotPartialResponseDto> items;
    MoneyPartialResponseDto price;
    Duration cooldown;
    String name;
    String visibleName;
    String description;

    protected CasePartialResponseDto() {

    }

    public CasePartialResponseDto(List<CaseSlotPartialResponseDto> items, MoneyPartialResponseDto price,
                                  Duration cooldown, String name, String visibleName, String description) {
        this.items = items;
        this.price = price;
        this.cooldown = cooldown;
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
    }

    @Override
    public List<CaseSlotPartialResponseDto> getItems() {
        return items;
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

    @Override
    public String getVisibleName() {
        return visibleName;
    }

    public void setCooldown(Duration cooldown) {
        this.cooldown = cooldown;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
