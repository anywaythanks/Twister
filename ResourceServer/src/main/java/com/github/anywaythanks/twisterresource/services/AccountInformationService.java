package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.services.GeneralAccountInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountInformationService {
    private final AccountMapper accountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountRepository accountRepository;
    private final GeneralAccountInformationService generalAccountInformationService;

    private Account getAccount(GeneralAccount generalAccount, AccountNumber number) {
        var account = generalAccount.getAccounts().get(number);
        if (account == null) throw new NotFoundException();
        return account;
    }

    public AccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                                AccountNumberRequestDto number) {
        var generalAccount = generalAccountRepository.findById(generalAccountInformationService.getId(name).getId()).orElseThrow(NotFoundException::new);
        return accountMapper.toPartialDTO(getAccount(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountIdResponseDto getId(GeneralAccountNameRequestDto name,
                                      AccountNumberRequestDto number) {
        var generalAccount = generalAccountRepository.findById(generalAccountInformationService.getId(name).getId()).orElseThrow(NotFoundException::new);
        return accountMapper.toIdDTO(getAccount(generalAccount, accountMapper.toNumber(number)));
    }

    public AccountDebitResponseDto getDebit(AccountNumberRequestDto number) {
        return accountMapper.toDebitDTO(accountRepository.findByNumber(accountMapper.toNumber(number))
                .orElseThrow(NotFoundException::new));
    }

    public AccountCreditResponseDto getCredit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number) {
        var generalAccount = generalAccountRepository.findById(generalAccountInformationService.getId(name).getId()).orElseThrow(NotFoundException::new);
        return accountMapper.toCreditDTO(getAccount(generalAccount, accountMapper.toNumber(number)));
    }

    public List<AccountPartialResponseDto> listPartial(GeneralAccountNameRequestDto name) {
        var generalAccount = generalAccountRepository.findById(generalAccountInformationService.getId(name).getId()).orElseThrow(NotFoundException::new);
        return generalAccount.getAccounts().values().stream().map(accountMapper::toPartialDTO).toList();
    }
}
