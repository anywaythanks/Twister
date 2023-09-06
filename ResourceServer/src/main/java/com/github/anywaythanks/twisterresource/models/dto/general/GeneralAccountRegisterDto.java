package com.github.anywaythanks.twisterresource.models.dto.general;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class GeneralAccountRegisterDto implements Name, Nickname, Uuid {
    @NonNull String nickname;
    @NonNull String name;
    @NonNull String uuid;
}
