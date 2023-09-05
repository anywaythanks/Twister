package com.github.anywaythanks.twisterresource.models.dto.acase;

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
public class CaseIdDto implements Id {
    @NonNull Long id;
}
