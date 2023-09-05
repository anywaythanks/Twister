package com.github.anywaythanks.twisterresource.models.dto.general;

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
public class GeneralAccountPartialResponseDto implements Name, Nickname {
    @NonNull String nickname;
    @NonNull String name;
}