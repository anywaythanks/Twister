package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventoryName;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;
import org.springframework.stereotype.Component;

@Component
public class InventoryMapper {
    private final SlotMapper slotMapper;

    public InventoryMapper(SlotMapper slotMapper) {
        this.slotMapper = slotMapper;
    }

    public InventoryDTO.Response.Name toNameDTO(InventoryName name) {
        return new InventoryDTO.Response.Name(name.getName());
    }

    public InventoryName toName(InventoryDTO.Request.Name name) {
        return new InventoryName(name.getName());
    }

    public InventoryDTO.Response.Id toIdDTO(Inventory inventory) {
        return new InventoryDTO.Response.Id(inventory.getId());
    }

    public InventoryDTO.Response.Credit toCreditDTO(Inventory inventory) {
        return new InventoryDTO.Response.Credit(inventory.getId());
    }

    public InventoryDTO.Response.Debit toDebitDTO(Inventory inventory) {
        return new InventoryDTO.Response.Debit(inventory.getId());
    }

    public Long toId(InventoryDTO.Response.Id id) {
        return id.getId();
    }

    public InventoryDTO.Response.Partial toPartialDTO(Inventory inventory) {
        return new InventoryDTO.Response.Partial(inventory
                .getInventorySlotMap().values().stream()
                .map(slotMapper::toPartialDTO).toList(),
                inventory.getName().getName());
    }
}
