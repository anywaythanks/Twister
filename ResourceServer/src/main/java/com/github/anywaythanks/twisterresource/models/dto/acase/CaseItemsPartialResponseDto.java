package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class CaseItemsPartialResponseDto implements Name, Cooldown, Items<CaseSlotPartialResponseDto>,
        Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull List<CaseSlotPartialResponseDto> items;
    @NonNull MoneyPartialResponseDto price;
    @With
    @NonNull Duration cooldown;
    @NonNull String name;
    @NonNull String visibleName;
    @NonNull String description;
}
