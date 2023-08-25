package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;

import java.util.List;

public class CasePartialWithoutCooldownResponseDto implements Name, Items<CaseSlotPartialResponseDto>,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    List<CaseSlotPartialResponseDto> items;
    MoneyPartialResponseDto price;
    String name;
    String visibleName;
    String description;

    protected CasePartialWithoutCooldownResponseDto() {

    }

    public CasePartialWithoutCooldownResponseDto(List<CaseSlotPartialResponseDto> items, MoneyPartialResponseDto price,
                                                 String name, String visibleName, String description) {
        this.items = items;
        this.price = price;
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
