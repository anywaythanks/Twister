package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;

public interface CaseInformationService {
    CaseDTO.Response.Partial getPartial(CaseDTO.Request.Name caseName);

    CaseDTO.Response.PartialWithoutCooldown getPartialWithoutCooldown(CaseDTO.Request.Name caseName);

    PageDTO.Response.Partial<CaseDTO.Response.LightPartialWithoutCooldown> getPageWithoutCooldown(Integer page, Integer size);
}
