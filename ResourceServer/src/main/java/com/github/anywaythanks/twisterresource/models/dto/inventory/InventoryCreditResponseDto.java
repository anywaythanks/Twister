package com.github.anywaythanks.twisterresource.models.dto.inventory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryCreditResponseDto extends InventoryIdResponseDto {
    public InventoryCreditResponseDto(@NonNull Long id) {
        super(id);
    }
}
