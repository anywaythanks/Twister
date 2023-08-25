package com.github.anywaythanks.twisterresource.models.dto.mappers.impl;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.inventory.*;
import com.github.anywaythanks.twisterresource.models.dto.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.SlotMapper;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapperImpl implements InventoryMapper {
    private final SlotMapper slotMapper;

    public InventoryMapperImpl(SlotMapper slotMapper) {
        this.slotMapper = slotMapper;
    }

    public InventoryNameResponseDto toNameDTO(InventoryName name) {
        return new InventoryNameResponseDto(name.getName());
    }

    public InventoryName toName(InventoryNameRequestDto name) {
        return new InventoryName(name.getName());
    }

    public InventoryIdResponseDto toIdDTO(Inventory inventory) {
        return new InventoryIdResponseDto(inventory.getId());
    }

    public InventoryCreditResponseDto toCreditDTO(Inventory inventory) {
        return new InventoryCreditResponseDto(inventory.getId());
    }

    public InventoryDebitResponseDto toDebitDTO(Inventory inventory) {
        return new InventoryDebitResponseDto(inventory.getId());
    }

    public Long toId(InventoryIdResponseDto id) {
        return id.getId();
    }

    public InventoryPartialResponseDto toPartialDTO(Inventory inventory) {
        return new InventoryPartialResponseDto(inventory
                .getInventorySlotMap().values().stream()
                .map(slotMapper::toPartialDTO).toList(),
                inventory.getName().getName());
    }
}
