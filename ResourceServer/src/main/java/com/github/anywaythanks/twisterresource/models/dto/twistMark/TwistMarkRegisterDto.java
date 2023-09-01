package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class TwistMarkRegisterDto implements Account<GeneralAccountIdResponseDto>, TwistCase<CaseIdDto> {
    @NonNull
    GeneralAccountIdResponseDto account;
    @NotNull
    CaseIdDto twistCase;
}
