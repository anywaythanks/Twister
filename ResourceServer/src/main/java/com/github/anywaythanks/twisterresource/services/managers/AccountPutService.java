package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.AccountNumber;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountPutService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final AccountMapper accountMapper;
    private final AccountRepository accountRepository;
    private final MoneyRegisterService registerMoneyService;
    private final AccountMergeService mergeAccountService;
    private final AccountRegisterService registerAccountService;

    @Transactional
    public AccountPartialResponseDto put(GeneralAccountNameRequestDto name, AccountNumberRequestDto numberDto,
                                         AccountCreateRequestDto create) {
        GeneralAccountIdAndUuidDto generalAccountId = generalAccountInformationService.getId(name);
        AccountNumber accountNumber = accountMapper.toNumber(numberDto);
        Optional<Account> optionalAccount = accountRepository.findContaining(generalAccountId.getId(), accountNumber);
        if (optionalAccount.isEmpty()) {
            Money amount = registerMoneyService.register(create.getType());
            return registerAccountService.register(accountMapper.toRegister(generalAccountId, accountNumber, amount));
        }
        Account account = optionalAccount.get();
        if (account.getAmount().getValue().compareTo(BigDecimal.ZERO) == 0) {
            Money amount = registerMoneyService.register(create.getType());
            account.setAmount(amount);
        }
        return mergeAccountService.merge(accountMapper.toFullDTO(generalAccountId, account));
    }
}
