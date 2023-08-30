package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountCreditResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotTransferRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TransferMoneyService {
    private final AccountRepository accountRepository;
    private final AccountInformationService accountInformationService;
    private final MoneyMapper moneyMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    private void actionAccount(AccountIdResponseDto accountId, MoneyCreateRequestDto val,
                               Function<Money, Function<Money, Money>> action) {
        Account account = accountRepository.findById(accountId.getId())
                .orElseThrow(NotFoundException::new);
        MoneyTypeIdResponseDto typeId = moneyTypeInformationService.getId(val.getType());
        MoneyType type = moneyTypeRepository.findById(typeId.getId())
                .orElseThrow(NotFoundException::new);
        Money actionVal = moneyMapper.toMoney(type, val);
        Money newVal = action.apply(account.getAmount()).apply(actionVal);
        if (newVal.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        account.setAmount(newVal);
    }

    @Transactional
    public void debit(AccountNumberRequestDto number, MoneyCreateRequestDto debit) {
        AccountDebitResponseDto accountDebit = accountInformationService.getDebit(number);
        actionAccount(accountDebit, debit, money -> money::add);
    }

    @Transactional
    public void credit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                       MoneyCreateRequestDto credit) {
        AccountCreditResponseDto accountCredit = accountInformationService.getCredit(name, number);
        actionAccount(accountCredit, credit, money -> money::subtract);
    }

    @Transactional
    public void transfer(GeneralAccountNameRequestDto generalAccountName, AccountNumberRequestDto source,
                         AccountNumberRequestDto recipient, MoneyCreateRequestDto value) {
        credit(generalAccountName, source, value);
        debit(recipient, value);
    }
}
