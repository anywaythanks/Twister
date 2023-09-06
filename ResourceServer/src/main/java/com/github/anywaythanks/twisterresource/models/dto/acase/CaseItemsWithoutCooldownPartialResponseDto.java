package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@ResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CaseItemsWithoutCooldownPartialResponseDto implements Name, Items<CaseSlotPartialResponseDto>, Price<MoneyPartialResponseDto>, VisibleName, Description {
    @NonNull List<CaseSlotPartialResponseDto> items;
    @NonNull MoneyPartialResponseDto price;
    @NonNull String name;
    @NonNull String visibleName;
    @NonNull String description;
}
