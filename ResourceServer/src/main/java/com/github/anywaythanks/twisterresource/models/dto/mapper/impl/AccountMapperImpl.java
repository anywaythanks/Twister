package com.github.anywaythanks.twisterresource.models.dto.mapper.impl;

import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyMapper;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {
    private final MoneyMapper moneyMapper;

    public AccountMapperImpl(MoneyMapper moneyMapper) {
        this.moneyMapper = moneyMapper;
    }

    public AccountDTO.Response.Partial toPartialDTO(Account account) {
        return new AccountDTO.Response.Partial(account.getNumber().getNumber(), moneyMapper.toPartialDTO(account.getAmount()));
    }

    public AccountDTO.Response.Id toIdDTO(Account account) {
        return new AccountDTO.Response.Id(account.getId());
    }

    public AccountDTO.Response.Debit toDebitDTO(Account account) {
        return new AccountDTO.Response.Debit(account.getId());
    }

    public AccountDTO.Response.Credit toCreditDTO(Account account) {
        return new AccountDTO.Response.Credit(account.getId());
    }

    public Long toId(AccountDTO.Response.Id id) {
        return id.getId();
    }

    public AccountNumber toNumber(AccountDTO.Request.Number number) {
        return new AccountNumber(number.getNumber());
    }

    public AccountDTO.Response.Number toNumber(Account account) {
        return new AccountDTO.Response.Number(account.getNumber().getNumber());
    }
}
