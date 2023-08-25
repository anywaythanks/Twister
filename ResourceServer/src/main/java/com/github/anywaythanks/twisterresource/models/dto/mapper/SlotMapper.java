package com.github.anywaythanks.twisterresource.models.dto.mappers;

import com.github.anywaythanks.twisterresource.models.Slot;
import com.github.anywaythanks.twisterresource.models.dto.slot.*;

public interface SlotMapper {
    SlotPartialResponseDto toPartialDTO(Slot<?> slot);

    SlotIdResponseDto toIdsDTO(Slot<?> slot);

    SlotTransferRequestDto toTransfer(Slot<?> slot);

    SlotTransferRequestDto toTransfer(SlotQuantityRequestDto quantity, SlotIdResponseDto slot);
}
