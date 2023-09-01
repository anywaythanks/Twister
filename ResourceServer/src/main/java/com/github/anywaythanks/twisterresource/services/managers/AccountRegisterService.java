package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountNumberRepository;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class AccountRegisterService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final AccountNumberRepository accountNumberRepository;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final MoneyRegisterService registerMoneyService;
    private final MoneyMapper moneyMapper;
    private final GeneralAccountMapper generalAccountMapper;

    @Transactional
    public AccountPartialResponseDto register(AccountRegisterDto accountRegisterDto) {
        Instant now = Instant.now();
        Account newAccount = new Account(accountMapper.toNumber(accountRegisterDto),
                moneyMapper.toMoney(accountRegisterDto.getAmount()), now, now,
                generalAccountMapper.toAccount(accountRegisterDto.getGeneral()));
        Account result = accountRepository.save(newAccount);
        return accountMapper.toPartialDTO(result);
    }

    @Transactional
    public AccountPartialResponseDto register(GeneralAccountNameRequestDto name, AccountCreateRequestDto create) {
        AccountNumber persistenceNumber = accountNumberRepository.save(new AccountNumber());
        Money amount = registerMoneyService.register(create.getType());
        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        return register(accountMapper.toRegister(generalAccountId, persistenceNumber, amount));
    }
}
