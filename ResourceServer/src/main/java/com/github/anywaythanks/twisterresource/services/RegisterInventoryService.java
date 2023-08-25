package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.InventoryDTO;

public interface RegisterInventoryService {
    InventoryDTO.Response.Partial register(GeneralAccountDTO.Request.Name name);
}
