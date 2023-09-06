package com.github.anywaythanks.twisterresource.models.dto.item;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public abstract class ItemFullDto implements Id, Name, VisibleName, CreatedOn, ModifiedBy {
    @NonNull Long id;
    @NonNull String name;
    @NonNull String visibleName;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
