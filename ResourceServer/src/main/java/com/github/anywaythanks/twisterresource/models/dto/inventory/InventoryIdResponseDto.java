package com.github.anywaythanks.twisterresource.models.dto.inventory;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class InventoryIdResponseDto implements Id {
    @NonNull
    Long id;
}
