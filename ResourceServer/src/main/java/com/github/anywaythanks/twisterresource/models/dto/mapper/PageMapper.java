package com.github.anywaythanks.twisterresource.models.dto.mappers;

import com.github.anywaythanks.twisterresource.models.dto.page.PagePartialResponseDto;

import java.util.List;

public interface PageMapper {
     <T> PagePartialResponseDto<T> toPartialDTO(List<T> values, Integer totalPages, Integer page);
}
