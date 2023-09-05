package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class CaseLightWithoutCooldownPartialResponseDto implements Name, Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull MoneyPartialResponseDto price;
    @NonNull String name;
    @NonNull String visibleName;
    @NonNull String description;
}
