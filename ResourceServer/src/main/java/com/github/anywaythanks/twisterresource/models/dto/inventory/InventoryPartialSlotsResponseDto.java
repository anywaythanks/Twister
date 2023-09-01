package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.models.dto.slot.SlotPartialResponseDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class InventoryPartialSlotsResponseDto implements Slots<SlotPartialResponseDto>, Name {
    @NonNull
    List<SlotPartialResponseDto> slots;
    @NonNull
    String name;
}
