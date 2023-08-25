package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CaseMapperImpl implements CaseMapper {
    private final ItemMapper itemMapper;
    private final MoneyMapper moneyMapper;

    public CaseMapperImpl(ItemMapper itemMapper,
                          MoneyMapper moneyMapper) {
        this.itemMapper = itemMapper;
        this.moneyMapper = moneyMapper;
    }

    public CasePartialResponseDto toPartialDTO(Case aCase) {
        return new CasePartialResponseDto(
                aCase.getCaseSlotSet().stream().map(this::toPartialDTO).toList(),
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getCooldown(),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CasePartialWithoutCooldownResponseDto toPartialWithoutCooldownDTO(Case aCase) {
        return new CasePartialWithoutCooldownResponseDto(
                aCase.getCaseSlotSet().stream().map(this::toPartialDTO).toList(),
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseCooldownIdResponseDto toCooldownIdDto(Case aCase) {
        return new CaseCooldownIdResponseDto(
                aCase.getId(),
                aCase.getCooldown());
    }

    public CaseLightPartialResponseDto toLightPartialDTO(Case aCase) {
        return new CaseLightPartialResponseDto(
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getCooldown(),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseLightPartialWithoutCooldownResponseDto toLightPartialWithoutCooldownDTO(Case aCase) {
        return new CaseLightPartialWithoutCooldownResponseDto(
                moneyMapper.toPartialDTO(aCase.getPrice()),
                aCase.getName(),
                aCase.getVisibleName(),
                aCase.getDescription());
    }

    public CaseSlotPartialResponseDto toPartialDTO(CaseSlot<?> slot) {
        return new CaseSlotPartialResponseDto(slot.getPercentageWining(),
                itemMapper.toPartialDTO(slot.getItem()),
                slot.getQuantityItem(), slot.getName().getName());
    }

    public CaseSlot<Item> toCaseSlot(Item item, CaseSlotCreateRequestDto request) {
        return new CaseSlot<>(item,
                request.getQuantity(),
                request.getPercentage());
    }

    public Long toId(CaseCooldownIdResponseDto id) {
        return id.getId();
    }

    public Case toCase(Set<CaseSlot<Item>> slots, CaseNameRequestDto name, MoneyType moneyType, CaseCreateRequestDto request) {
        return new Case(name.getName(), request.getVisibleName(), request.getDescription(),
                moneyMapper.toMoney(moneyType, request.getPrice()), slots, request.getCooldown());
    }

    public CaseNameResponseDto toName(Case nCase) {
        return new CaseNameResponseDto(nCase.getName());
    }
}
