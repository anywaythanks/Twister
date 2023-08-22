package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import org.springframework.stereotype.Component;

@Component
public class SlotMapper {
    public final ItemMapper itemMapper;

    public SlotMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public SlotDTO.Response.Partial toPartialDTO(Slot<?> slot) {
        return new SlotDTO.Response.Partial(slot.getQuantityItem(), itemMapper.toPartialDTO(slot.getItem()));
    }

    public SlotDTO.Response.Ids toIdsDTO(Slot<?> slot) {
        return new SlotDTO.Response.Ids(slot.getQuantityItem(), itemMapper.toIdDTO(slot.getItem()), slot.getId());
    }

    public SlotDTO.Request.Transfer toTransfer(Slot<?> slot) {
        return new SlotDTO.Request.Transfer(slot.getQuantityItem(), itemMapper.toIdDTO(slot.getItem()));
    }

    public SlotDTO.Request.Transfer toTransfer(SlotDTO.Request.Quantity quantity, SlotDTO.Response.Ids slot) {
        return new SlotDTO.Request.Transfer(quantity.getQuantity(), slot.getItem());
    }
}
