package com.github.anywaythanks.twisterresource.models.dto.general;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class GeneralAccountFullDto implements Name, Nickname, Id, Uuid, CreatedOn, ModifiedBy {
    @NonNull Long id;
    @NonNull String uuid;
    @NonNull String nickname;
    @NonNull String name;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
