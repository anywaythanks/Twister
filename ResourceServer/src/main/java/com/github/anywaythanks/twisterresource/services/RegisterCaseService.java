package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;

public interface RegisterCaseService {
    CaseDTO.Response.Partial merge(CaseDTO.Request.Name name, CaseDTO.Request.Create create);
}
