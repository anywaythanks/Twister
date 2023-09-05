package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class CaseCreateRequestDto implements Cooldown, Items<CaseSlotCreateRequestDto>, Price<MoneyCreateRequestDto>, VisibleName, Description {
    @NonNull List<CaseSlotCreateRequestDto> items;
    @NonNull MoneyCreateRequestDto price;
    @NonNull Duration cooldown;
    @NonNull String visibleName;
    @NonNull String description;
}
