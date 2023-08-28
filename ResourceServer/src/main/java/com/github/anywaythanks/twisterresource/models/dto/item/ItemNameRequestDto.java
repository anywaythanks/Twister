package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class ItemNameRequestDto implements Name {
    @NonNull
    String name;
}
