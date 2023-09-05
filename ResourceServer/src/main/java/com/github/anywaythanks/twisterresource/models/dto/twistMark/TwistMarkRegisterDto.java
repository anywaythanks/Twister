package com.github.anywaythanks.twisterresource.models.dto.twistMark;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class TwistMarkRegisterDto implements Account<GeneralAccountIdAndUuidDto>, TwistCase<CaseIdDto> {
    @NonNull GeneralAccountIdAndUuidDto account;
    @NonNull CaseIdDto twistCase;
}
