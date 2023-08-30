package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.GeneralAccount;
import com.github.anywaythanks.twisterresource.models.dto.account.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountInformationService {
    private final AccountMapper accountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountRepository accountRepository;
    private final GeneralAccountInformationService generalAccountInformationService;

    private Account getAccount(GeneralAccountNameRequestDto name,
                               AccountNumberRequestDto accountNumberDto) {
        GeneralAccountIdResponseDto id = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(id.getId())
                .orElseThrow(NotFoundException::new);
        AccountNumber number = accountMapper.toNumber(accountNumberDto);
        Account account = generalAccount.getAccounts().get(number);
        if (account == null) throw new NotFoundException();
        return account;
    }

    public AccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                                AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(name, accountNumberDto);
        return accountMapper.toPartialDTO(account);
    }

    public AccountIdResponseDto getId(GeneralAccountNameRequestDto name,
                                      AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(name, accountNumberDto);
        return accountMapper.toIdDTO(account);
    }

    public AccountDebitResponseDto getDebit(AccountNumberRequestDto accountNumberDto) {
        AccountNumber number = accountMapper.toNumber(accountNumberDto);
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(NotFoundException::new);
        return accountMapper.toDebitDTO(account);
    }

    public AccountCreditResponseDto getCredit(GeneralAccountNameRequestDto name,
                                              AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(name, accountNumberDto);
        return accountMapper.toCreditDTO(account);
    }

    @Transactional(readOnly = true)
    public List<AccountPartialResponseDto> listPartial(GeneralAccountNameRequestDto name) {
        GeneralAccountIdResponseDto id = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(id.getId())
                .orElseThrow(NotFoundException::new);
        return generalAccount
                .getAccounts()
                .values()
                .stream()
                .map(accountMapper::toPartialDTO)
                .toList();
    }
}
