package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public abstract class ItemFullDto implements Id, Name, VisibleName, CreatedOn, ModifiedBy {
    @NonNull
    Long id;
    @NonNull
    String name;
    @NonNull
    String visibleName;
    @NonNull
    Instant createdOn;
    @NonNull
    Instant modifiedBy;
}
