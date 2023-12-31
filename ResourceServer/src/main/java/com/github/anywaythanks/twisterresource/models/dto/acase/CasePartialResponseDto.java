package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CasePartialResponseDto implements Name, Cooldown, Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull MoneyPartialResponseDto price;
    @With
    @NonNull Duration cooldown;
    @NonNull String name;
    @NonNull String visibleName;
    @NonNull String description;
}
