package com.github.anywaythanks.twisterresource.models.dto.mapper.impl;

import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.PageMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageMapperImpl implements PageMapper {
    public <T> PageDTO.Response.Partial<T> toPartialDTO(List<T> values, Integer totalPages, Integer page) {
        return new PageDTO.Response.Partial<>(values, totalPages, page);
    }
}
