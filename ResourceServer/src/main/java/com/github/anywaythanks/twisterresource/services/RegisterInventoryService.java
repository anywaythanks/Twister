package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryPartialResponseDto;

public interface RegisterInventoryService {
    InventoryPartialResponseDto register(GeneralAccountNameRequestDto name);
}
