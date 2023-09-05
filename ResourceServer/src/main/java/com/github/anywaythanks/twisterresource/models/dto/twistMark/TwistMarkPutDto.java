package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.annotation.MappingDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@MappingDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class TwistMarkPutDto implements Account<GeneralAccountIdAndUuidDto>, Consider, TwistCase<CaseIdDto> {
    @NonNull Boolean consider;
    @NonNull GeneralAccountIdAndUuidDto account;
    @NonNull CaseIdDto twistCase;
}
