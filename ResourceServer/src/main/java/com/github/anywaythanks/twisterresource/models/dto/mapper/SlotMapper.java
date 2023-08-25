package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.SlotDTO;
import org.springframework.stereotype.Component;

public interface SlotMapper {
    SlotDTO.Response.Partial toPartialDTO(Slot<?> slot);

    SlotDTO.Response.Id toIdsDTO(Slot<?> slot);

    SlotDTO.Request.Transfer toTransfer(Slot<?> slot);

    SlotDTO.Request.Transfer toTransfer(SlotDTO.Request.Quantity quantity, SlotDTO.Response.Id slot);
}
