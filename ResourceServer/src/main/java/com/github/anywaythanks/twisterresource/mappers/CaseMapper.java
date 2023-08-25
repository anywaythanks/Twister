package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;

import java.util.Set;

public interface CaseMapper {
    CasePartialResponseDto toPartialDTO(Case aCase);

    CasePartialWithoutCooldownResponseDto toPartialWithoutCooldownDTO(Case aCase);

    CaseCooldownIdResponseDto toCooldownIdDto(Case aCase);

    CaseLightPartialResponseDto toLightPartialDTO(Case aCase);

    CaseLightPartialWithoutCooldownResponseDto toLightPartialWithoutCooldownDTO(Case aCase);

    CaseSlotPartialResponseDto toPartialDTO(CaseSlot<?> slot);

    CaseSlot<Item> toCaseSlot(Item item, CaseSlotCreateRequestDto request);

    Long toId(CaseCooldownIdResponseDto id);

    Case toCase(Set<CaseSlot<Item>> slots, CaseNameRequestDto name, MoneyType moneyType, CaseCreateRequestDto request);

    CaseNameResponseDto toName(Case nCase);
}
