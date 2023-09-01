package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.CooldownException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.mappers.TwistMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCooldownIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.AccountRepository;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.TwistRepository;
import com.github.anywaythanks.twisterresource.services.managers.CaseActualInformationService;
import com.github.anywaythanks.twisterresource.services.managers.GeneralAccountInformationService;
import com.github.anywaythanks.twisterresource.services.managers.TwistMarkPutService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TwistService {
    private final TwistRepository twistRepository;
    private final GeneralAccountInformationService generalAccountInformationService;
    private final TwistMapper twistMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountMapper accountMapper;
    private final SlotMapper slotMapper;
    private final CaseActualInformationService caseActualInformationService;
    private final MoneyMapper moneyMapper;
    private final CaseRepository caseRepository;
    private final ShopService shopService;
    private final AccountRepository accountRepository;
    private final TwistMarkPutService twistMarkPutService;

    @Transactional
    public TwistPartialResponseDto twist(GeneralAccountNameRequestDto name,
                                         InventoryNameRequestDto nameInventory,
                                         AccountNumberRequestDto number,
                                         CaseNameRequestDto caseName) throws NoSuchAlgorithmException {
        CaseCooldownIdResponseDto cooldownId = caseActualInformationService.getCooldownId(name, caseName);
        if (!cooldownId.getCooldown().isZero())
            throw new CooldownException();
        twistMarkPutService.put();
        Case twistedCase = caseRepository.findById(cooldownId.getId())
                .orElseThrow(NotFoundException::new);
        CaseSlot<Item> wonSlot = twist(twistedCase.getCaseSlotSet());

        MoneyCreateRequestDto price = moneyMapper.toRequest(twistedCase.getPrice());
        shopService.buy(name, nameInventory, slotMapper.toTransfer(wonSlot), number, price);
        GeneralAccountIdResponseDto generalId = generalAccountInformationService.getId(name);
        GeneralAccount generalAccount = generalAccountRepository.findById(generalId.getId())
                .orElseThrow(NotFoundException::new);
        TwistNumber newNumber = new TwistNumber();
        Account account = accountRepository.findContaining(generalAccount.getId(), accountMapper.toNumber(number))
                .orElseThrow(NotFoundException::new);
        Twist<?> resultTwist = new Twist<>(account, generalAccount, newNumber, twistedCase, wonSlot.getItem(),
                wonSlot.getQuantityItem(), Instant.now());
        return twistMapper.toDTO(wonSlot, twistRepository.save(resultTwist));
    }

    private CaseSlot<Item> twist(Collection<CaseSlot<Item>> collectionOrderedByPercentage) throws NoSuchAlgorithmException {
        CaseSlot<Item> wonSlot = collectionOrderedByPercentage.iterator().next();
        BigDecimal sum = BigDecimal.ZERO;
        var random = SecureRandom.getInstanceStrong();
        BigDecimal dice = BigDecimal.valueOf(random.nextDouble());
        for (var slot : collectionOrderedByPercentage) {
            wonSlot = slot;
            sum = sum.add(slot.getPercentageWining());
            if (sum.compareTo(dice) > 0) break;
        }
        return wonSlot;
    }
}
