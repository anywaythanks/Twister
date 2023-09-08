package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface CaseMapper {
    @Mapping(source = "items", target = "items")
    @Mapping(target = "withCooldown", ignore = true)
    CaseItemsPartialResponseDto toPartialItemsDTO(List<CaseSlotPartialResponseDto> items, Case aCase);

    @Mapping(source = "items", target = "items")
    CaseItemsWithoutCooldownPartialResponseDto toPartialWithoutCooldownDTO(List<CaseSlotPartialResponseDto> items, Case aCase);

    @Mapping(target = "withCooldown", ignore = true)
    CaseLightPartialResponseDto toLightPartialDTO(Case aCase);

    CaseLightWithoutCooldownPartialResponseDto toLightPartialWithoutCooldownDTO(Case aCase);

    @Mapping(target = "withCooldown", ignore = true)
    CasePartialResponseDto toPartialDTO(Case aCase);

    @Mapping(source = "idDto.id", target = "id")
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "visibleName", ignore = true)
    @Mapping(target = "description", ignore = true)
    @Mapping(target = "price", ignore = true)
    @Mapping(target = "cooldown", ignore = true)
    @Mapping(target = "createdOn", ignore = true)
    @Mapping(target = "modifiedBy", ignore = true)
    Case toCase(CaseIdDto idDto);

    Case toCase(CaseFullDto fullDto);

    @Mapping(target = "withCooldown", ignore = true)
    CaseFullDto toFull(Case aCase);

    @Mapping(source = "items", target = "items")
    @Mapping(source = "name.name", target = "name")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "create.cooldown", target = "cooldown")
    @Mapping(source = "create.visibleName", target = "visibleName")
    @Mapping(source = "create.description", target = "description")
    CaseRegisterDto toRegister(List<CaseSlotRegisterDto> items, CaseNameRequestDto name,
                               MoneyFullDto price, CaseCreateRequestDto create);

    CaseIdDto toCaseId(Case aCase);

    CaseNameResponseDto toName(Case nCase);
    CaseNameRequestDto toNameRequest(Case nCase);
}
