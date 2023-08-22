package com.github.anywaythanks.twisterresource.models.dto.mapper;

import com.github.anywaythanks.twisterresource.models.dto.PageDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PageMapper {
    public <T> PageDTO.Response.Partial<T> toDTO(List<T> values, Integer totalPages, Integer page) {
        return new PageDTO.Response.Partial<>(values, totalPages, page);
    }
}
