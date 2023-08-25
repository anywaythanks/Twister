package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.dto.PageDTO;

import java.util.List;

public interface PageMapper {
     <T> PageDTO.Response.Partial<T> toPartialDTO(List<T> values, Integer totalPages, Integer page);
}
