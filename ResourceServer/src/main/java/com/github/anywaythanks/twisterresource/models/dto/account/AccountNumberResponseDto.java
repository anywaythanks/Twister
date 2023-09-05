package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@ResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class AccountNumberResponseDto implements Number {
    @NonNull String number;
}
