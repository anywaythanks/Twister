package com.github.anywaythanks.twisterresource.models.dto.inventory;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class InventoryNameResponseDto implements Name {
    @NonNull
    String name;
}
