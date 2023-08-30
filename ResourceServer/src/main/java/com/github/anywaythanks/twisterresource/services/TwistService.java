package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.CooldownException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.AccountMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.mappers.TwistMapper;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.TwistNumber;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCooldownIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.TwistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
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

    @Transactional
    public TwistPartialResponseDto twist(GeneralAccountNameRequestDto name,
                                         InventoryNameRequestDto nameInventory,
                                         AccountNumberRequestDto number,
                                         CaseNameRequestDto caseName) throws NoSuchAlgorithmException {
        CaseCooldownIdResponseDto cooldownId = caseActualInformationService.getCooldownId(name, caseName);
        if (!cooldownId.getCooldown().isZero())
            throw new CooldownException();
        var twistedCase = caseRepository.findById(cooldownId.getId())
                .orElseThrow(NotFoundException::new);
        var wonSlot = twist(twistedCase.getCaseSlotSet());
        var price = moneyMapper.toRequest(twistedCase.getPrice());
        shopService.buy(name, nameInventory, slotMapper.toTransfer(wonSlot), number, price);
        var generalId = generalAccountInformationService.getId(name);
        var generalAccount = generalAccountRepository.findById(generalId.getId())
                .orElseThrow(NotFoundException::new);
        var newNumber = new TwistNumber();
        var account = generalAccount.getAccounts().get(accountMapper.toNumber(number));
        var resultTwist = new Twist<>(account, generalAccount, newNumber, twistedCase, wonSlot.getItem(),
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
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return wonSlot;
    }
}
