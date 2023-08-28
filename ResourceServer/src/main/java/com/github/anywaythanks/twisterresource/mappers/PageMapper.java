package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialWithoutCooldownResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PageMapper {
    CasePagePartialResponseDto toPartialDTO(List<CaseLightPartialResponseDto> values, Integer totalPages, Integer page);

    CaseWithoutCooldownPagePartialResponseDto toPartialWithoutCooldownDTO(List<CaseLightPartialWithoutCooldownResponseDto> values,
                                                           Integer totalPages, Integer page);
}
