package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class TransferMoneyService {
    private final AccountRepository accountRepository;
    private final AccountInformationService accountInformationService;
    private final MoneyMapper moneyMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;

    public void debit(AccountNumberRequestDto number, MoneyCreateRequestDto debit) {
        final var account = accountRepository.findById(accountInformationService.getDebit(number).getId())
                .orElseThrow(NotFoundException::new);
        var type = moneyTypeRepository.findById(moneyTypeInformationService.getId(debit.getType()).getId()).orElseThrow(NotFoundException::new);
        account.setAmount(account.getAmount().add(moneyMapper.toMoney(type, debit)));
    }

    public void credit(GeneralAccountNameRequestDto name, AccountNumberRequestDto number,
                       MoneyCreateRequestDto credit) {
        final var account = accountRepository.findById(accountInformationService.getCredit(name, number).getId())
                .orElseThrow(NotFoundException::new);
        var type = moneyTypeRepository.findById(moneyTypeInformationService.getId(credit.getType()).getId()).orElseThrow(NotFoundException::new);
        final var newVal = account.getAmount().subtract(moneyMapper.toMoney(type, credit));
        if (newVal.getValue().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsufficientFundsException();
        }
        account.setAmount(newVal);
    }

    public void transfer(GeneralAccountNameRequestDto generalAccountName, AccountNumberRequestDto source,
                         AccountNumberRequestDto recipient, MoneyCreateRequestDto value) {
        credit(generalAccountName, source, value);
        debit(recipient, value);
    }
}
