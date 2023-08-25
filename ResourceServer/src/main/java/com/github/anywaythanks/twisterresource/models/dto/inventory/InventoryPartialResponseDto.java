package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;

import java.util.List;

public class InventoryPartialResponseDto implements Slots<SlotPartialResponseDto>, Name {
    List<SlotPartialResponseDto> slots;
    String name;

    protected InventoryPartialResponseDto() {
    }

    public InventoryPartialResponseDto(List<SlotPartialResponseDto> slots, String name) {
        this.slots = slots;
        this.name = name;
    }

    @Override
    public List<SlotPartialResponseDto> getSlots() {
        return slots;
    }

    @Override
    public String getName() {
        return name;
    }
}
