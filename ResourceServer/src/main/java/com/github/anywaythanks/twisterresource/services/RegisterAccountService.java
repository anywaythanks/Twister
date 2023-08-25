package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;

public interface RegisterAccountService {
    AccountDTO.Response.Partial merge(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number,
                                             AccountDTO.Request.Create create);

    AccountDTO.Response.Partial register(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Create create);
}
