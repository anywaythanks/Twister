package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO.Request.Number;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO.Request.Name;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.services.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountInformationServiceImpl implements AccountInformationService {
    private final AccountMapper accountMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountRepository accountRepository;
    private final GeneralAccountInformationService generalAccountInformationService;

    public AccountInformationServiceImpl(AccountMapper accountMapper,
                                         GeneralAccountMapper generalAccountMapper,
                                         GeneralAccountRepository generalAccountRepository,
                                         AccountRepository accountRepository,
                                         GeneralAccountInformationService generalAccountInformationService) {
        this.accountMapper = accountMapper;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.accountRepository = accountRepository;
        this.generalAccountInformationService = generalAccountInformationService;
    }

    private Account get(GeneralAccount generalAccount, AccountNumber number) {
        var account = generalAccount.getAccounts().get(number);
        if (account == null) throw new NotFoundException();
        return account;
    }

    public AccountDTO.Response.Partial getPartial(Name name,
                                                  Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toPartialDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountDTO.Response.Id getId(Name name,
                                        Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toIdDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountDTO.Response.Debit getDebit(Number number) {
        return accountMapper.toDebitDTO(accountRepository.findByNumber(accountMapper.toNumber(number))
                .orElseThrow(NotFoundException::new));
    }

    public AccountDTO.Response.Credit getCredit(Name name, Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toCreditDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public List<AccountDTO.Response.Partial> listPartial(Name name) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return generalAccount.getAccounts().values().stream().map(accountMapper::toPartialDTO).toList();
    }
}
