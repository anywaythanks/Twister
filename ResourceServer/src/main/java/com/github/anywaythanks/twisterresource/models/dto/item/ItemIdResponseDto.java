package com.github.anywaythanks.twisterresource.models.dto.item;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class ItemIdResponseDto implements Id {
    @NonNull
    Long id;
}
