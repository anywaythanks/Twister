package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.ItemDTO;

import java.util.List;

public interface ItemInformationService {
    ItemDTO.Response.Partial getPartial(ItemDTO.Request.Name name);

    ItemDTO.Response.Id getId(ItemDTO.Request.Name name);

    List<ItemDTO.Response.Partial> listPartial();
}
