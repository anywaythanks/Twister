package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.AccountMapper;
import com.github.anywaythanks.twisterresource.models.dto.mapper.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountInformationService {
    private final AccountMapper accountMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountRepository accountRepository;
    private final GeneralAccountInformationService generalAccountInformationService;

    public AccountInformationService(AccountMapper accountMapper,
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

    public AccountDTO.Response.Partial getPartial(GeneralAccountDTO.Request.Name name,
                                                  AccountDTO.Request.Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toPartialDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountDTO.Response.Id getId(GeneralAccountDTO.Request.Name name,
                                        AccountDTO.Request.Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toIdDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountDTO.Response.Debit getDebit(AccountDTO.Request.Number number) {
        return accountMapper.toDebitDTO(accountRepository.findByNumber(accountMapper.toNumber(number))
                .orElseThrow(NotFoundException::new));
    }

    public AccountDTO.Response.Credit getCredit(GeneralAccountDTO.Request.Name name, AccountDTO.Request.Number number) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return accountMapper.toCreditDTO(get(generalAccount, accountMapper.toNumber(number)));
    }

    public List<AccountDTO.Response.Partial> listPartial(GeneralAccountDTO.Request.Name name) {
        var generalAccount = generalAccountRepository.findById(generalAccountMapper.toId(
                generalAccountInformationService.getId(name))).orElseThrow(NotFoundException::new);
        return generalAccount.getAccounts().values().stream().map(accountMapper::toPartialDTO).toList();
    }
}
