package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class AccountMergeService {
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final Clock clock;

    @Transactional
    public AccountPartialResponseDto merge(AccountFullDto accountFullDto) {
        Account account = accountMapper.toAccount(accountFullDto);
        account.setModifiedBy(Instant.now(clock));
        Account result = accountRepository.save(account);
        return accountMapper.toPartialDTO(result);
    }
}
