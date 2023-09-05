package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.annotation.IdDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@IdDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class MoneyTypeIdResponseDto implements Id {
    @NonNull Integer id;
}
