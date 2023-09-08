package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@InformationService
@RequiredArgsConstructor
public class AccountInformationService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final GeneralAccountInformationService generalAccountInformationService;

    private Account getAccount(GeneralAccountIdAndUuidDto id,
                               AccountNumberRequestDto accountNumberDto) {
        AccountNumber number = accountMapper.toNumber(accountNumberDto);
        return accountRepository.findContaining(id.getId(), number)
                .orElseThrow(NotFoundException::new);
    }

    private Account getAccount(GeneralAccountNameRequestDto name,
                               AccountNumberRequestDto accountNumberDto) {
        GeneralAccountIdAndUuidDto id = generalAccountInformationService.getId(name);
        return getAccount(id, accountNumberDto);
    }

    public AccountFullDto getFull(GeneralAccountNameRequestDto name,
                                  AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(name, accountNumberDto);
        return accountMapper.toFullDTO(account);
    }

    public AccountFullDto getFull(GeneralAccountIdAndUuidDto generalAccountIdResponseDto,
                                  AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(generalAccountIdResponseDto, accountNumberDto);
        return accountMapper.toFullDTO(account);
    }

    public AccountDebitResponseDto getPublic(AccountNumberRequestDto accountNumberDto) {
        AccountNumber number = accountMapper.toNumber(accountNumberDto);
        Account account = accountRepository.findByNumber(number)
                .orElseThrow(NotFoundException::new);
        return accountMapper.toDebitDTO(account);
    }

    public AccountPartialResponseDto getPartial(GeneralAccountNameRequestDto name,
                                                AccountNumberRequestDto accountNumberDto) {
        Account account = getAccount(name, accountNumberDto);
        return accountMapper.toPartialDTO(account);
    }

    public List<AccountPartialResponseDto> getPartials(GeneralAccountNameRequestDto name) {
        GeneralAccountIdAndUuidDto id = generalAccountInformationService.getId(name);
        return accountRepository.findAllByGeneralAccountId(id.getId())
                .stream()
                .map(accountMapper::toPartialDTO)
                .toList();
    }
}
