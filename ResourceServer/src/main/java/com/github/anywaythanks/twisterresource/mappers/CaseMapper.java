package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Mapper(uses = {ItemMapper.class, MoneyMapper.class}, componentModel = "spring")
public interface CaseMapper {
    @Mapping(source = "aCase.caseSlotSet", target = "items")
    CasePartialResponseDto toPartialDTO(Case aCase);

    @Mapping(source = "aCase.caseSlotSet", target = "items")
    CasePartialWithoutCooldownResponseDto toPartialWithoutCooldownDTO(Case aCase);

    CaseCooldownIdResponseDto toCooldownIdDto(Case aCase);

    CaseLightPartialResponseDto toLightPartialDTO(Case aCase);

    CaseLightPartialWithoutCooldownResponseDto toLightPartialWithoutCooldownDTO(Case aCase);

    @Mappings({
            @Mapping(source = "slot.item", target = "item"),
            @Mapping(source = "slot.name.name", target = "name"),
            @Mapping(source = "slot.quantityItem", target = "quantity"),
            @Mapping(source = "slot.percentageWining", target = "percentage")
    })
    CaseSlotPartialResponseDto toPartialDTO(CaseSlot<? extends Item> slot);

    @Mappings({
            @Mapping(source = "item", target = "item"),
            @Mapping(source = "request.quantity", target = "quantityItem"),
            @Mapping(source = "request.percentage", target = "percentageWining")
    })
    CaseSlot<Item> toCaseSlot(Item item, CaseSlotCreateRequestDto request);

    @Mappings({
            @Mapping(source = "slots", target = "caseSlotSet"),
            @Mapping(source = "name.name", target = "name"),
            @Mapping(expression = "java(moneyMapper.toMoney(moneyType, request.getPrice()))", target = "price"),
            @Mapping(source = "now", target = "modifiedBy"),
            @Mapping(source = "now", target = "createdOn")
    })
    Case toCase(Instant now,Set<CaseSlot<Item>> slots, CaseNameRequestDto name, MoneyType moneyType, CaseCreateRequestDto request);

    CaseNameResponseDto toName(Case nCase);

    default List<CaseSlotPartialResponseDto> toPartialsDto(Set<CaseSlot<Item>> set) {
        return set.stream().map(this::toPartialDTO).toList();
    }
}
