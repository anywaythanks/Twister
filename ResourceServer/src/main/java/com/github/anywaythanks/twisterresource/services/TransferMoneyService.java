package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;

public interface TransferMoneyService {
    void debit(AccountNumberRequestDto number, MoneyCreateRequestDto debit);

    void credit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                MoneyCreateRequestDto credit);

    void transfer(GeneralAccountNameRequestDto generalAccountName, AccountNumberRequestDto source,
                  AccountNumberRequestDto recipient, MoneyCreateRequestDto value);
}
