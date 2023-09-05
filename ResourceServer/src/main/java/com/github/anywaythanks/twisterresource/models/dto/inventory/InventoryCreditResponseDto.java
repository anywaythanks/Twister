package com.github.anywaythanks.twisterresource.models.dto.inventory;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import lombok.NonNull;

@ResponseDto
public class InventoryCreditResponseDto extends InventoryIdDto {
    public InventoryCreditResponseDto(@NonNull Long id) {
        super(id);
    }
}
