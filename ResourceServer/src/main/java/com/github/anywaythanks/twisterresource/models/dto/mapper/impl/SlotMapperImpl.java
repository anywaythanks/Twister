package com.github.anywaythanks.twisterresource.models.dto.mappers.impl;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.slot.*;
import com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.SlotMapper;
import org.springframework.stereotype.Component;

@Component
public class SlotMapperImpl implements SlotMapper {
    public final com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper itemMapper;

    public SlotMapperImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    public SlotPartialResponseDto toPartialDTO(Slot<?> slot) {
        return new SlotPartialResponseDto(slot.getQuantityItem(), itemMapper.toPartialDTO(slot.getItem()));
    }

    public SlotIdResponseDto toIdsDTO(Slot<?> slot) {
        return new SlotIdResponseDto(slot.getQuantityItem(), itemMapper.toIdDTO(slot.getItem()), slot.getId());
    }

    public SlotTransferRequestDto toTransfer(Slot<?> slot) {
        return new SlotTransferRequestDto(slot.getQuantityItem(), itemMapper.toIdDTO(slot.getItem()));
    }

    public SlotTransferRequestDto toTransfer(SlotQuantityRequestDto quantity, SlotIdResponseDto slot) {
        return new SlotTransferRequestDto(quantity.getQuantity(), slot.getItem());
    }
}
