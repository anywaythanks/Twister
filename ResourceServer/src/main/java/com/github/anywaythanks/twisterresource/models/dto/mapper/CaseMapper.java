package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CaseMapper {
    private final ItemMapper itemMapper;
    private final MoneyMapper moneyMapper;

    public CaseMapper(ItemMapper itemMapper,
                      MoneyMapper moneyMapper) {
        this.itemMapper = itemMapper;
        this.moneyMapper = moneyMapper;
    }

    public CaseDTO.Response.Partial toPartialDTO(Case aCase) {
        return new CaseDTO.Response.Partial(
                aCase.getCaseSlotSet().stream().map(this::toPartialDTO).toList(),
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getCooldown(),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseDTO.Response.PartialWithoutCooldown toPartialWithoutCooldownDTO(Case aCase) {
        return new CaseDTO.Response.PartialWithoutCooldown(
                aCase.getCaseSlotSet().stream().map(this::toPartialDTO).toList(),
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseDTO.Response.CooldownId toCooldownIdDto(Case aCase) {
        return new CaseDTO.Response.CooldownId(
                aCase.getId(),
                aCase.getCooldown());
    }

    public CaseDTO.Response.LightPartial toLightPartialDTO(Case aCase) {
        return new CaseDTO.Response.LightPartial(
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getCooldown(),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseDTO.Response.LightPartialWithoutCooldown toLightPartialWithoutCooldownDTO(Case aCase) {
        return new CaseDTO.Response.LightPartialWithoutCooldown(
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseDTO.Slot.Response.Partial toPartialDTO(CaseSlot<?> slot) {
        return new CaseDTO.Slot.Response.Partial(slot.getPercentageWining(),
                itemMapper.toPartialDTO(slot.getItem()),
                slot.getQuantityItem(), slot.getName().getName());
    }

    public CaseSlot<Item> toCaseSlot(Item item, CaseDTO.Slot.Request.Create request) {
        return new CaseSlot<>(item,
                request.getQuantity(),
                request.getPercentage());
    }

    public Long toId(CaseDTO.Response.CooldownId id) {
        return id.getId();
    }

    public Case toCase(Set<CaseSlot<Item>> slots, CaseDTO.Request.Name name, MoneyType moneyType, CaseDTO.Request.Create request) {
        return new Case(name.getName(), request.getVisibleName(), request.getDescription(),
                moneyMapper.toMoney(moneyType, request.getPrice()), slots, request.getCooldown());
    }

    public CaseDTO.Response.Name toName(Case nCase) {
        return new CaseDTO.Response.Name(nCase.getName());
    }
}
