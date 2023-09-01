package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.*;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CasePartialResponseDto implements Name, Cooldown,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull
    MoneyPartialResponseDto price;
    @NonNull
    @With
    Duration cooldown;
    @NonNull
    String name;
    @NonNull
    String visibleName;
    @NonNull
    String description;
}
