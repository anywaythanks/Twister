package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.annotation.RegisterDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@RegisterDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class MoneyTypeRegisterDto implements Name, PathToIcon {
    @NonNull String name;
    @NonNull String pathToIcon;
}
