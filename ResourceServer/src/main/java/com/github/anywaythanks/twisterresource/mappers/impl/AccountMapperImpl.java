package com.github.anywaythanks.twisterresource.mappers.impl;

import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import org.springframework.stereotype.Component;

@Component
public class AccountMapperImpl implements AccountMapper {
    private final MoneyMapper moneyMapper;

    public AccountMapperImpl(MoneyMapper moneyMapper) {
        this.moneyMapper = moneyMapper;
    }

    public AccountPartialResponseDto toPartialDTO(Account account) {
        return new AccountPartialResponseDto(moneyMapper.toPartialDTO(account.getAmount()), account.getNumber().getNumber());
    }

    public AccountIdResponseDto toIdDTO(Account account) {
        return new AccountIdResponseDto(account.getId());
    }

    public AccountDebitResponseDto toDebitDTO(Account account) {
        return new AccountDebitResponseDto(account.getId());
    }

    public AccountCreditResponseDto toCreditDTO(Account account) {
        return new AccountCreditResponseDto(account.getId());
    }

    public Long toId(AccountIdResponseDto id) {
        return id.getId();
    }

    public AccountNumber toNumber(AccountNumberRequestDto number) {
        return new AccountNumber(number.getNumber());
    }

    public AccountNumberResponseDto toNumber(Account account) {
        return new AccountNumberResponseDto(account.getNumber().getNumber());
    }
}
