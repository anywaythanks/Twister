package com.github.anywaythanks.twisterresource.models.dto.page;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialWithoutCooldownResponseDto;
import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseWithoutCooldownPagePartialResponseDto implements Values<CaseLightPartialWithoutCooldownResponseDto>, Total, Num {
    @NonNull
    private List<CaseLightPartialWithoutCooldownResponseDto> values;
    @NonNull
    private Integer totalPages;
    @NonNull
    private Integer page;
}
