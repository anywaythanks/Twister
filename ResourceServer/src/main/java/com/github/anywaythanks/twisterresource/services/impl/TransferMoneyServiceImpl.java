package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.TransferMoneyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TransferMoneyServiceImpl implements TransferMoneyService {
    private final AccountRepository accountRepository;
    private final AccountInformationService accountInformationService;
    private final AccountMapper accountMapper;
    private final MoneyMapper moneyMapper;
    private final MoneyTypeMapper moneyTypeMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    public TransferMoneyServiceImpl(AccountRepository accountRepository,
                                    AccountInformationService accountInformationService,
                                    AccountMapper accountMapper,
                                    MoneyMapper moneyMapper,
                                    MoneyTypeMapper moneyTypeMapper,
                                    MoneyTypeInformationService moneyTypeInformationService,
                                    MoneyTypeRepository moneyTypeRepository) {
        this.accountRepository = accountRepository;
        this.accountInformationService = accountInformationService;
        this.accountMapper = accountMapper;
        this.moneyMapper = moneyMapper;
        this.moneyTypeMapper = moneyTypeMapper;
        this.moneyTypeInformationService = moneyTypeInformationService;
        this.moneyTypeRepository = moneyTypeRepository;
    }

    public void debit(AccountNumberRequestDto number, MoneyCreateRequestDto debit) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService.getDebit(number)))
                .orElseThrow(NotFoundException::new);
        var type = moneyTypeRepository.findById(moneyTypeMapper
                .toId(moneyTypeInformationService.getId(debit.getType()))).orElseThrow(NotFoundException::new);
        account.setAmount(account.getAmount().add(moneyMapper.toMoney(type, debit)));
    }

    public void credit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                       MoneyCreateRequestDto credit) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService.getCredit(name, number)))
                .orElseThrow(NotFoundException::new);
        var type = moneyTypeRepository.findById(moneyTypeMapper
                .toId(moneyTypeInformationService.getId(credit.getType()))).orElseThrow(NotFoundException::new);
        final var newVal = account.getAmount().subtract(moneyMapper.toMoney(type, credit));
        if (newVal.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        account.setAmount(newVal);
    }

    public void transfer(GeneralAccountNameRequestDto generalAccountName, AccountNumberRequestDto source,
                         AccountNumberRequestDto recipient, MoneyCreateRequestDto value) {
        credit(generalAccountName, source, value);
        debit(recipient, value);
    }
}
