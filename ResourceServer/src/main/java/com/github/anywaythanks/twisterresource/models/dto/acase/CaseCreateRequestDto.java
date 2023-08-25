package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;

import java.time.Duration;
import java.util.List;

public class CaseCreateRequestDto implements Cooldown,
        Items<CaseSlotCreateRequestDto>, Price<MoneyCreateRequestDto>, VisibleName, Description {
    List<CaseSlotCreateRequestDto> items;
    MoneyCreateRequestDto price;
    Duration cooldown;
    String visibleName;
    String description;

    protected CaseCreateRequestDto() {
    }

    public CaseCreateRequestDto(List<CaseSlotCreateRequestDto> items, MoneyCreateRequestDto price,
                                Duration cooldown, String visibleName, String description) {
        this.items = items;
        this.price = price;
        this.cooldown = cooldown;
        this.visibleName = visibleName;
        this.description = description;
    }

    @Override
    public List<CaseSlotCreateRequestDto> getItems() {
        return items;
    }

    @Override
    public MoneyCreateRequestDto getPrice() {
        return price;
    }

    @Override
    public Duration getCooldown() {
        return cooldown;
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
