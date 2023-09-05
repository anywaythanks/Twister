package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.Account;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.services.managers.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.managers.AccountMergeService;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransferMoneyService {
    private final AccountInformationService accountInformationService;
    private final MoneyMapper moneyMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final AccountMapper accountMapper;
    private final AccountMergeService mergeAccountService;
    private final MoneyTypeMapper moneyTypeMapper;

    private void actionAccount(AccountFullDto accountFullDto, MoneyFullDto val,
                               Function<Money, Function<Money, Money>> action) {
        Account account = accountMapper.toAccount(accountFullDto);
        Money actionVal = moneyMapper.toMoney(val);
        Money newVal = action.apply(account.getAmount()).apply(actionVal);
        if (newVal.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        account.setAmount(newVal);
        mergeAccountService.merge(accountFullDto);
    }

    @Transactional
    public void debit(AccountNumberRequestDto number, MoneyFullDto debit) {
        AccountDebitResponseDto accountDebit = accountInformationService.getPublic(number);
        actionAccount(accountDebit, debit, money -> money::add);
    }

    @Transactional
    public void credit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                       MoneyFullDto credit) {
        AccountFullDto accountCredit = accountInformationService.getFull(name, number);
        actionAccount(accountCredit, credit, money -> money::subtract);
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto generalAccountName, AccountNumberRequestDto source,
                         AccountNumberRequestDto recipient, MoneyCreateRequestDto value) {
        MoneyTypeFullDto typeFull = moneyTypeInformationService.getFull(value.getType());
        MoneyFullDto moneyFull = moneyMapper.toFull(typeFull, value);
        credit(generalAccountName, source, moneyFull);
        debit(recipient, moneyFull);
    }
}
