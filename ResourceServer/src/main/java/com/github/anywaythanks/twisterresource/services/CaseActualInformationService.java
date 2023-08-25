package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.PageDTO;

public interface CaseActualInformationService {
    CaseDTO.Response.CooldownId getCooldownId(GeneralAccountDTO.Request.Name name,
                                              CaseDTO.Request.Name caseName);

    CaseDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name,
                                        CaseDTO.Request.Name caseName);

    PageDTO.Response.Partial<CaseDTO.Response.LightPartial> getPage(int page, int size,
                                                                    GeneralAccountDTO.Request.Name name);
}
