package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.services.managers.CaseRegisterService;
import lombok.*;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseRegisterDto implements Cooldown,
        Items<CaseSlotRegisterDto>, Price<MoneyFullDto>, VisibleName, Description, Name {
    @NonNull
    List<CaseSlotRegisterDto> items;
    @NonNull
    String name;
    @NonNull
    MoneyFullDto price;
    @NonNull
    Duration cooldown;
    @NonNull
    String visibleName;
    @NonNull
    String description;
}