package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CaseRegisterDto implements Cooldown, Items<CaseSlotRegisterDto>, Price<MoneyFullDto>, VisibleName, Description, Name {
    @NonNull List<CaseSlotRegisterDto> items;
    @NonNull String name;
    @NonNull MoneyFullDto price;
    @NonNull Duration cooldown;
    @NonNull String visibleName;
    @NonNull String description;
}