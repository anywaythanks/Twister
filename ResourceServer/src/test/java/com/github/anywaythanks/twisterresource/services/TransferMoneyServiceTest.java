package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.config.MapstructConfig;
import com.github.anywaythanks.twisterresource.exceptions.InsufficientFundsException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.services.managers.AccountInformationService;
import com.github.anywaythanks.twisterresource.services.managers.AccountMergeService;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(MapstructConfig.class)
class TransferMoneyServiceTest {
    @Autowired
    @Spy
    MoneyMapper moneyMapper;
    @Autowired
    GeneralAccountMapper generalAccountMapper;
    @Autowired
    @Spy
    AccountMapper accountMapper;
    @Mock
    MoneyTypeInformationService moneyTypeInformationService;
    @Mock
    AccountMergeService mergeAccountService;
    @Mock
    MoneyUtils moneyUtils;
    @Mock
    AccountInformationService accountInformationService;
    TransferMoneyService transferMoneyService;
    MoneyType type1, type2;
    Account account;
    Clock clock = Clock.systemUTC();

    void initAccount() {
        GeneralAccountName generalAccountName = GeneralAccountName.builder()
                .name("g5")
                .build();
        GeneralAccount generalAccount = GeneralAccount.builder()
                .id(5L)
                .userUuid("faaa340c-a418-4ae6-b5eb-c18976d7b171")
                .name(generalAccountName)
                .nickname("n5")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        type1 = MoneyType.builder()
                .id(123)
                .pathToIcon("/45")
                .name("123")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        type2 = MoneyType.builder()
                .id(312)
                .pathToIcon("/54")
                .name("321")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        Money amount = Money.builder()
                .moneyType(type1)
                .value(BigDecimal.valueOf(815))
                .build();
        AccountNumber accountNumber = AccountNumber.builder()
                .number("54312")
                .build();
        account = Account.builder()
                .id(467L)
                .generalAccount(generalAccount)
                .number(accountNumber)
                .amount(amount)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
    }

    void initMocks() {
        transferMoneyService = new TransferMoneyService(accountInformationService, moneyMapper, moneyTypeInformationService, accountMapper, mergeAccountService, moneyUtils);
    }

    @BeforeEach
    void init() {
        initAccount();
        initMocks();
    }

    @Nested
    class Debit {
        @Test
        void debit() {
            AccountNumberRequestDto numberRequestDto = accountMapper.toNumberRequest(account.getNumber());
            AccountDebitResponseDto debit = accountMapper.toDebitDTO(account);
            when(accountInformationService.getPublic(numberRequestDto)).thenReturn(debit);
            Money addition = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(10))
                    .build();
            Money sum = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(825))
                    .build();
            when(moneyUtils.add(account.getAmount(), addition)).thenReturn(sum);
            transferMoneyService.debit(numberRequestDto, moneyMapper.toFull(addition));

            account.setAmount(sum);
            AccountFullDto accountFullDto = accountMapper.toFullDTO(account);
            verify(mergeAccountService, times(1)).merge(accountFullDto);
        }
    }

    @Nested
    class Credit {

        @Test
        void creditInsufficientFunds() {
            GeneralAccountNameRequestDto generalName = generalAccountMapper
                    .toNameRequest(account.getGeneralAccount().getName());
            AccountNumberRequestDto numberRequestDto = accountMapper.toNumberRequest(account.getNumber());
            AccountFullDto full = accountMapper.toFullDTO(account);
            when(accountInformationService.getFull(generalName, numberRequestDto)).thenReturn(full);
            Money credited = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(1000))
                    .build();
            Money diff = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(-185))
                    .build();
            when(moneyUtils.subtract(account.getAmount(), credited)).thenReturn(diff);
            InsufficientFundsException exception = assertThrows(InsufficientFundsException.class,
                    () -> transferMoneyService.credit(generalName, numberRequestDto, moneyMapper.toFull(credited)));
            assertEquals(exception.getMessage(), "Insufficient funds on the account.");
        }

        @Test
        void credit() {
            GeneralAccountNameRequestDto generalName = generalAccountMapper
                    .toNameRequest(account.getGeneralAccount().getName());
            AccountNumberRequestDto numberRequestDto = accountMapper.toNumberRequest(account.getNumber());
            AccountFullDto full = accountMapper.toFullDTO(account);
            when(accountInformationService.getFull(generalName, numberRequestDto)).thenReturn(full);
            Money credited = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(10))
                    .build();
            Money diff = Money.builder()
                    .moneyType(type1)
                    .value(BigDecimal.valueOf(805))
                    .build();
            when(moneyUtils.subtract(account.getAmount(), credited)).thenReturn(diff);
            transferMoneyService.credit(generalName, numberRequestDto, moneyMapper.toFull(credited));

            account.setAmount(diff);
            AccountFullDto accountFullDto = accountMapper.toFullDTO(account);
            verify(mergeAccountService, times(1)).merge(accountFullDto);
        }
    }
}