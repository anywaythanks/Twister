package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;

public interface RegisterItemService {
    ItemDTO.Response.Partial merge(ItemDTO.Request.Name name, ItemDTO.Request.CreateItem mergeItem);
}
