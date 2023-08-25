package com.github.anywaythanks.twisterresource.models.dto.page;

import java.util.List;

public class PagePartialResponseDto<T> implements Values<T>, Total, Num {
    private List<T> values;
    private Integer totalPages;
    private Integer page;

    public PagePartialResponseDto() {
    }

    public PagePartialResponseDto(List<T> values, Integer totalPages, Integer page) {
        this.values = values;
        this.totalPages = totalPages;
        this.page = page;
    }

    @Override
    public List<T> getValues() {
        return values;
    }

    @Override
    public Integer getTotalPages() {
        return totalPages;
    }

    @Override
    public Integer getPage() {
        return page;
    }
}
