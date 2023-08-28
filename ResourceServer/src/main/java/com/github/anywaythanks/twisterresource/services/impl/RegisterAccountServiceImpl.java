package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountNumberRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterAccountServiceImpl implements RegisterAccountService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final AccountNumberRepository accountNumberRepository;
    private final AccountMapper accountMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeMapper moneyTypeMapper;

    public AccountPartialResponseDto merge(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                                           AccountCreateRequestDto create) {
        var type = moneyTypeRepository.findById(moneyTypeInformationService.
                getId(create.getType()).getId()).orElseThrow(NotFoundException::new);
        var account = new Account(accountMapper.toNumber(number), new Money(BigDecimal.ZERO, type));
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pNumber = accountNumberRepository.save(accountMapper.toNumber(number));
        generalAccount.getAccounts().putIfAbsent(pNumber, account);
        var accountPersistence = generalAccount.getAccounts().get(pNumber);
        accountPersistence.setNumber(pNumber);
        if (accountPersistence.getAmount().getValue().compareTo(BigDecimal.ZERO) == 0)
            accountPersistence.setAmount(account.getAmount());
        return accountMapper.toPartialDTO(accountPersistence);
    }

    public AccountPartialResponseDto register(GeneralAccountNameRequestDto name, AccountCreateRequestDto create) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pNumber = accountNumberRepository.save(new AccountNumber());
        var type = moneyTypeRepository.findById(moneyTypeInformationService.
                getId(create.getType()).getId()).orElseThrow(NotFoundException::new);
        var account = new Account(pNumber, new Money(BigDecimal.ZERO, type));
        generalAccount.getAccounts().put(account.getNumber(), account);
        return accountMapper.toPartialDTO(generalAccount.getAccounts().get(pNumber));
    }
}
