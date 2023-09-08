package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountNumberRepository;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
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
    private final Clock clock;
    @Transactional
    public AccountPartialResponseDto register(AccountRegisterDto accountRegisterDto) {
        Instant now = Instant.now(clock);
        AccountNumber number = (accountMapper.toNumber(accountRegisterDto));
        GeneralAccount generalAccount = generalAccountMapper.toAccount(accountRegisterDto.getGeneral());
        Account newAccount = Account.builder()
                .number(accountNumberRepository.save(number))
                .amount(moneyMapper.toMoney(accountRegisterDto.getAmount()))
                .createdOn(now)
                .modifiedBy(now)
                .generalAccount(generalAccount)
                .build();
        Account result = accountRepository.save(newAccount);
        return accountMapper.toPartialDTO(result);
    }

    @Transactional
    public AccountPartialResponseDto register(GeneralAccountNameRequestDto name, AccountCreateRequestDto create) {
        AccountNumber persistenceNumber = accountNumberRepository.save(AccountNumber.builder().build());
        Money amount = registerMoneyService.register(create.getType());
        GeneralAccountIdAndUuidDto generalAccountId = generalAccountInformationService.getId(name);
        return register(accountMapper.toRegister(generalAccountId, persistenceNumber, amount));
    }
}
