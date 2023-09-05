package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.annotation.CreateRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@CreateRequestDto
@FieldDefaults(level = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor
@Getter
public class MoneyTypeCreateRequestDto implements PathToIcon {
    @NonNull String pathToIcon;
}
