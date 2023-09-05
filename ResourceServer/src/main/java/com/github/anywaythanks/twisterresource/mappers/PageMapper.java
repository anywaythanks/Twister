package com.github.anywaythanks.twisterresource.mappers;

import com.github.anywaythanks.twisterresource.configs.MapstructConfig;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightWithoutCooldownPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CasePagePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.page.CaseWithoutCooldownPagePartialResponseDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(config = MapstructConfig.class)
public interface PageMapper {
    CasePagePartialResponseDto toPartialDTO(List<CaseLightPartialResponseDto> values, Integer totalPages, Integer page);

    CaseWithoutCooldownPagePartialResponseDto toPartialWithoutCooldownDTO(List<CaseLightWithoutCooldownPartialResponseDto> values,
                                                                          Integer totalPages, Integer page);
}
