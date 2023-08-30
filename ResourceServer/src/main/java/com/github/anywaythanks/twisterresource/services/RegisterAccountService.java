package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.repository.AccountNumberRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class RegisterAccountService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final AccountNumberRepository accountNumberRepository;
    private final AccountMapper accountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;

    private Money createAmount(AccountCreateRequestDto create) {
        MoneyTypeIdResponseDto moneyTypeId = moneyTypeInformationService.getId(create.getType());
        MoneyType type = moneyTypeRepository.findById(moneyTypeId.getId())
                .orElseThrow(NotFoundException::new);
        return new Money(BigDecimal.ZERO, type);
    }

    @Transactional
    public AccountPartialResponseDto merge(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                                           AccountCreateRequestDto create) {
        Money amount = createAmount(create);
        AccountNumber accountNumber = accountMapper.toNumber(number);
        Account mergedAccount = new Account(accountNumber, amount);

        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalAccountId.getId())
                .orElseThrow(NotFoundException::new);

        AccountNumber persistenceNumber = accountNumberRepository.save(accountMapper.toNumber(number));
        generalAccount.getAccounts().putIfAbsent(persistenceNumber, mergedAccount);

        Account accountPersistence = generalAccount.getAccounts().get(persistenceNumber);
        //accountPersistence.setNumber(persistenceNumber);TODO
        if (accountPersistence.getAmount().getValue().compareTo(BigDecimal.ZERO) == 0)
            accountPersistence.setAmount(mergedAccount.getAmount());
        return accountMapper.toPartialDTO(accountPersistence);
    }

    @Transactional
    public AccountPartialResponseDto register(GeneralAccountNameRequestDto name, AccountCreateRequestDto create) {
        AccountNumber persistenceNumber = accountNumberRepository.save(new AccountNumber());
        Money amount = createAmount(create);
        Account newAccount = new Account(persistenceNumber, amount);

        GeneralAccountIdResponseDto generalAccountId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalAccountId.getId())
                .orElseThrow(NotFoundException::new);
        generalAccount.getAccounts().put(newAccount.getNumber(), newAccount);
        return accountMapper.toPartialDTO(generalAccount.getAccounts().get(persistenceNumber));
    }
}
