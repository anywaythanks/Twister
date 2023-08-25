package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;

public interface TransferMoneyService {
    void debit(AccountDTO.Request.Number number, MoneyDTO.Request.Create debit);

    void credit(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number,
                       MoneyDTO.Request.Create credit);

    void transfer(GeneralAccountDTO.Request.Name generalAccountName, AccountDTO.Request.Number source,
                         AccountDTO.Request.Number recipient, MoneyDTO.Request.Create value);
}
