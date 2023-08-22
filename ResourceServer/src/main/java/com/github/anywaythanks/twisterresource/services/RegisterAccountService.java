package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.AccountNumberRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class RegisterAccountService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final AccountNumberRepository accountNumberRepository;
    private final AccountMapper accountMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeMapper moneyTypeMapper;

    public RegisterAccountService(GeneralAccountInformationService generalAccountInformationService,
                                  AccountNumberRepository accountNumberRepository,
                                  AccountMapper accountMapper,
                                  GeneralAccountMapper generalAccountMapper,
                                  GeneralAccountRepository generalAccountRepository,
                                  MoneyTypeRepository moneyTypeRepository,
                                  MoneyTypeInformationService moneyTypeInformationService,
                                  MoneyTypeMapper moneyTypeMapper) {
        this.generalAccountInformationService = generalAccountInformationService;
        this.accountNumberRepository = accountNumberRepository;
        this.accountMapper = accountMapper;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.moneyTypeRepository = moneyTypeRepository;
        this.moneyTypeInformationService = moneyTypeInformationService;
        this.moneyTypeMapper = moneyTypeMapper;
    }

    public AccountDTO.Response.Partial merge(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number,
                                             AccountDTO.Request.Create create) {
        var type = moneyTypeRepository.findById(moneyTypeMapper.toId(moneyTypeInformationService.
                getId(create.getType()))).orElseThrow(NotFoundException::new);
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

    public AccountDTO.Response.Partial register(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Create create) {
        var generalAccount = generalAccountRepository
                .findById(generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var pNumber = accountNumberRepository.save(new AccountNumber());
        var type = moneyTypeRepository.findById(moneyTypeMapper.toId(moneyTypeInformationService.
                getId(create.getType()))).orElseThrow(NotFoundException::new);
        var account = new Account(pNumber, new Money(BigDecimal.ZERO, type));
        generalAccount.getAccounts().put(account.getNumber(), account);
        return accountMapper.toPartialDTO(generalAccount.getAccounts().get(pNumber));
    }
}
