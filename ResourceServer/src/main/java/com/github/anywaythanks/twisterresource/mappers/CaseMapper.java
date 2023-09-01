package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;

@Mapper(config = MapstructConfig.class)
public interface CaseMapper {
    @Mapping(source = "items", target = "items")
    @Mapping(target = "withCooldown", ignore = true)
    CasePartialItemsResponseDto toPartialItemsDTO(List<CaseSlotPartialResponseDto> items, Case aCase);

    @Mapping(source = "items", target = "items")
    CasePartialItemsWithoutCooldownResponseDto toPartialWithoutCooldownDTO(List<CaseSlotPartialResponseDto> items, Case aCase);

    @Mapping(target = "withCooldown", ignore = true)
    CaseCooldownIdResponseDto toCooldownIdDto(Case aCase);

    @Mapping(target = "withCooldown", ignore = true)
    CaseLightPartialResponseDto toLightPartialDTO(Case aCase);

    CaseLightPartialWithoutCooldownResponseDto toLightPartialWithoutCooldownDTO(Case aCase);

    @Mappings({
            @Mapping(source = "slot.item", target = "item"),
            @Mapping(source = "slot.name.name", target = "name"),
            @Mapping(source = "slot.quantityItem", target = "quantity"),
            @Mapping(source = "slot.percentageWining", target = "percentage")
    })
    CaseSlotPartialResponseDto toPartialDTO(CaseSlot<? extends Item> slot);

    CasePartialResponseDto toPartialDTO(Case aCase);

    @Mappings({
            @Mapping(source = "item", target = "item"),
            @Mapping(source = "request.quantity", target = "quantityItem"),
            @Mapping(source = "request.percentage", target = "percentageWining")
    })
    CaseSlot<Item> toCaseSlot(Item item, CaseSlotCreateRequestDto request);

    Case toCase(CaseIdDto caseIdDto);

    CaseIdDto toCaseId(Case aCase);

    CaseNameResponseDto toName(Case nCase);

    default List<CaseSlotPartialResponseDto> toPartialsDto(Set<CaseSlot<Item>> set) {
        return set.stream().map(this::toPartialDTO).toList();
    }
}
