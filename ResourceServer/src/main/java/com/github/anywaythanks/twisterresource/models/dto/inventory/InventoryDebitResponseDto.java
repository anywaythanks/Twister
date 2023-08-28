package com.github.anywaythanks.twisterresource.models.dto.inventory;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventoryDebitResponseDto extends InventoryIdResponseDto {
    public InventoryDebitResponseDto(@NonNull Long id) {
        super(id);
    }
}
