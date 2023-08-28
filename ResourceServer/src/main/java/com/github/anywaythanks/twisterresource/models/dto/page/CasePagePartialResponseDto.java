package com.github.anywaythanks.twisterresource.models.dto.page;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import lombok.*;

import java.util.List;
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CasePagePartialResponseDto implements Values<CaseLightPartialResponseDto>, Total, Num {
    @NonNull
    private List<CaseLightPartialResponseDto> values;
    @NonNull
    private Integer totalPages;
    @NonNull
    private Integer page;
}
