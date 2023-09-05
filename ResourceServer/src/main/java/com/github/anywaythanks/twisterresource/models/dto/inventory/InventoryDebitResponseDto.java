package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import lombok.NonNull;

@ResponseDto
public class InventoryDebitResponseDto extends InventoryIdDto {
    public InventoryDebitResponseDto(@NonNull Long id) {
        super(id);
    }
}
