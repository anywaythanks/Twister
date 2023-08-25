package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import org.springframework.stereotype.Component;

@Component
public class SlotMapperImpl implements SlotMapper {
    public final ItemMapper itemMapper;

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
