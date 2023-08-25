package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;

import java.util.List;

public interface AccountInformationService {
    AccountDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name,
                                           AccountDTO.Request.Number number);

    AccountDTO.Response.Id getId(GeneralAccountDTO.Request.Name name,
                                 AccountDTO.Request.Number number);

    AccountDTO.Response.Debit getDebit(AccountDTO.Request.Number number);

    AccountDTO.Response.Credit getCredit(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number);

    List<AccountDTO.Response.Partial> listPartial(GeneralAccountDTO.Request.Name name);
}
