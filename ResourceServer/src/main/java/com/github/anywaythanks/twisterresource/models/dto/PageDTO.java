package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public class PageDTO {
    private interface Values<T> {
        @NotNull
        List<T> getValues();
    }

    private interface Total {
        @PositiveOrZero
        @NotNull
        Integer getTotalPages();
    }

    private interface Num {
        @PositiveOrZero
        @NotNull
        Integer getPage();
    }

    public enum Response {
        ;

        public static class Partial<T> implements Values<T>, Total, Num {
            private List<T> values;
            private Integer totalPages;
            private Integer page;

            public Partial() {
            }

            public Partial(List<T> values, Integer totalPages, Integer page) {
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
    }
}
