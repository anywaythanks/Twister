package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

public interface CaseMapper {
    CaseDTO.Response.Partial toPartialDTO(Case aCase);

    CaseDTO.Response.PartialWithoutCooldown toPartialWithoutCooldownDTO(Case aCase);

    CaseDTO.Response.CooldownId toCooldownIdDto(Case aCase);

    CaseDTO.Response.LightPartial toLightPartialDTO(Case aCase);

    CaseDTO.Response.LightPartialWithoutCooldown toLightPartialWithoutCooldownDTO(Case aCase);

    CaseDTO.Slot.Response.Partial toPartialDTO(CaseSlot<?> slot);

    CaseSlot<Item> toCaseSlot(Item item, CaseDTO.Slot.Request.Create request);

    Long toId(CaseDTO.Response.CooldownId id);

    Case toCase(Set<CaseSlot<Item>> slots, CaseDTO.Request.Name name, MoneyType moneyType, CaseDTO.Request.Create request);

    CaseDTO.Response.Name toName(Case nCase);
}
