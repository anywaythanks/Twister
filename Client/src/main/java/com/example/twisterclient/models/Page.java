package com.example.twisterclient.models;

import java.util.List;

public class Page<T> {
    Long page;
    Long totalPages;
    List<T> values;

    protected Page() {
    }

    public Page(Long page, Long totalPages, List<T> values) {
        this.page = page;
        this.totalPages = totalPages;
        this.values = values;
    }

    public Long getPage() {
        return page;
    }

    public void setPage(Long page) {
        this.page = page;
    }

    public Long getTotalPages() {
        return totalPages;
    }

    public List<T> getValues() {
        return values;
    }
}
