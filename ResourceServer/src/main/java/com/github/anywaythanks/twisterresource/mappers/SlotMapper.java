package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;

public interface SlotMapper {
    SlotPartialResponseDto toPartialDTO(Slot<?> slot);

    SlotIdResponseDto toIdsDTO(Slot<?> slot);

    SlotTransferRequestDto toTransfer(Slot<?> slot);

    SlotTransferRequestDto toTransfer(SlotQuantityRequestDto quantity, SlotIdResponseDto slot);
}
