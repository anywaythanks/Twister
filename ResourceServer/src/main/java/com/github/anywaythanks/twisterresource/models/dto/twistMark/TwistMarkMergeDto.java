package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class TwistMarkMergeDto implements Id, Account<GeneralAccountIdResponseDto>, Consider,
        TwistCase<CaseIdDto>, CreatedOn, ModifiedBy {
    @NonNull
    Long id;
    @NonNull
    Boolean consider;
    @NonNull
    GeneralAccountIdResponseDto account;
    @NotNull
    CaseIdDto twistCase;
    @NonNull
    Instant createdOn;
    @NonNull
    Instant modifiedBy;
}
