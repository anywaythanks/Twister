package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class TwistMarkPutDto implements Account<GeneralAccountIdResponseDto>, Consider,
        TwistCase<CaseIdDto> {
    @NonNull
    Boolean consider;
    @NonNull
    GeneralAccountIdResponseDto account;
    @NotNull
    CaseIdDto twistCase;
}
