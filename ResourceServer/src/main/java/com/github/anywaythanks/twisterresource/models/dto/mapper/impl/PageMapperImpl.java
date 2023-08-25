package com.github.anywaythanks.twisterresource.models.dto.mappers.impl;

import com.github.anywaythanks.twisterresource.models.dto.mappers.PageMapper;
import com.github.anywaythanks.twisterresource.models.dto.page.PagePartialResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageMapperImpl implements PageMapper {
    public <T> PagePartialResponseDto<T> toPartialDTO(List<T> values, Integer totalPages, Integer page) {
        return new PagePartialResponseDto<>(values, totalPages, page);
    }
}
