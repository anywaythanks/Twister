package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class TwistMarkFullDto implements Id, Account<GeneralAccountIdAndUuidDto>, Consider,
        TwistCase<CaseIdDto>, CreatedOn, ModifiedBy {
    @NonNull Long id;
    @NonNull Boolean consider;
    @NonNull GeneralAccountIdAndUuidDto account;
    @NonNull CaseIdDto twistCase;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
