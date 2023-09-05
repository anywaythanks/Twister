package com.github.anywaythanks.twisterresource.models.dto.account;

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
public class AccountIdDto implements Id {
    @NonNull Long id;
}
