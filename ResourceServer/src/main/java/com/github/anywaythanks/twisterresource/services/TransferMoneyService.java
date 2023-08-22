package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class TransferMoneyService {
    private final AccountRepository accountRepository;
    private final AccountInformationService accountInformationService;
    private final AccountMapper accountMapper;
    private final MoneyMapper moneyMapper;
    private final MoneyTypeMapper moneyTypeMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    public TransferMoneyService(AccountRepository accountRepository,
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

    public void debit(AccountDTO.Request.Number number, MoneyDTO.Request.Create debit) {
        final var account = accountRepository.findById(accountMapper.toId(accountInformationService.getDebit(number)))
                .orElseThrow(NotFoundException::new);
        var type = moneyTypeRepository.findById(moneyTypeMapper
                .toId(moneyTypeInformationService.getId(debit.getType()))).orElseThrow(NotFoundException::new);
        account.setAmount(account.getAmount().add(moneyMapper.toMoney(type, debit)));
    }

    public void credit(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number,
                       MoneyDTO.Request.Create credit) {
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

    public void transfer(GeneralAccountDTO.Request.Name generalAccountName, AccountDTO.Request.Number source,
                         AccountDTO.Request.Number recipient, MoneyDTO.Request.Create value) {
        credit(generalAccountName, source, value);
        debit(recipient, value);
    }
}
