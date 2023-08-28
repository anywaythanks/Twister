package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import lombok.*;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseCreateRequestDto implements Cooldown,
        Items<CaseSlotCreateRequestDto>, Price<MoneyCreateRequestDto>, VisibleName, Description {
    @NonNull
    List<CaseSlotCreateRequestDto> items;
    @NonNull
    MoneyCreateRequestDto price;
    @NonNull
    Duration cooldown;
    @NonNull
    String visibleName;
    @NonNull
    String description;
}
