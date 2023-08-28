package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public abstract class ItemPartialResponseDto implements Name, Type, VisibleName {
    @NonNull
    ItemTypes type;
    @NonNull
    String name;
    @NonNull
    String visibleName;
}
