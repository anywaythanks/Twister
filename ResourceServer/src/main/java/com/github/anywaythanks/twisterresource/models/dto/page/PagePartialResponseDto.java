package com.github.anywaythanks.twisterresource.models.dto.page;

import lombok.*;

import java.util.List;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class PagePartialResponseDto<T> implements Values<T>, Total, Num {
    @NonNull
    private List<T> values;
    @NonNull
    private Integer totalPages;
    @NonNull
    private Integer page;
}
