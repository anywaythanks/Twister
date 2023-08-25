package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;

import java.util.List;

public interface MoneyTypeInformationService {
    List<MoneyDTO.Type.Response.Partial> listPartial();

    MoneyDTO.Type.Response.Partial getPartial(MoneyDTO.Type.Request.Name name);

    MoneyDTO.Type.Response.Id getId(MoneyDTO.Type.Request.Name name);
}
