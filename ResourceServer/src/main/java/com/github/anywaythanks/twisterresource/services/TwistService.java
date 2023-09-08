package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.CooldownException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.mappers.TwistMarkMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseIdDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.services.managers.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class TwistService {
    private final GeneralAccountInformationService generalAccountInformationService;
    private final TwistMarkMapper twistMarkMapper;
    private final SlotMapper slotMapper;
    private final CaseActualInformationService caseActualInformationService;
    private final MoneyMapper moneyMapper;
    private final ShopService shopService;
    private final TwistMarkPutService twistMarkPutService;
    private final CaseMapper caseMapper;
    private final CaseSlotInformationService caseSlotInformationService;
    private final AccountInformationService accountInformationService;
    private final TwistRegisterService twistRegisterService;
    private final TwistMarkMergeService twistMarkMergeService;
    private final TwistMarkInformationService twistMarkInformationService;

    @Transactional
    public TwistPartialResponseDto twist(GeneralAccountNameRequestDto name,
                                         InventoryNameRequestDto nameInventory,
                                         AccountNumberRequestDto number,
                                         CaseNameRequestDto caseName) {
        CaseFullDto caseFullDto = caseActualInformationService.getFull(name, caseName);
        Case twistedCase = caseMapper.toCase(caseFullDto);
        CaseIdDto caseIdDto = caseMapper.toCaseId(twistedCase);
        if (!twistedCase.getCooldown().isZero())
            throw new CooldownException();
        GeneralAccountIdAndUuidDto generalAccountIdDto = generalAccountInformationService.getId(name);
        twistMarkPutService.putIfAbsent(twistMarkMapper.toPut(generalAccountIdDto, twistedCase));
        twistMarkMergeService.merge(twistMarkInformationService.getFull(generalAccountIdDto, caseIdDto).withConsider(true));
        CaseSlot<Item> wonSlot = twist(caseSlotInformationService
                .getFullsOrdersPercent(caseIdDto)
                .stream()
                .map(slotMapper::toCaseSlot)
                .toList());
        MoneyCreateRequestDto price = moneyMapper.toRequest(twistedCase.getPrice());
        shopService.buy(name, nameInventory, slotMapper.toAction(wonSlot), number, price);
        AccountFullDto accountIdDto = accountInformationService.getFull(generalAccountIdDto, number);
        return twistRegisterService.register(caseFullDto, generalAccountIdDto, accountIdDto, slotMapper.toCaseSlotFull(wonSlot));
    }

    private CaseSlot<Item> twist(Collection<CaseSlot<Item>> collectionOrderedByPercentage) {
        CaseSlot<Item> wonSlot = collectionOrderedByPercentage.iterator().next();
        BigDecimal sum = BigDecimal.ZERO;
        SecureRandom random;
        try {
            random = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);//TODO
        }
        BigDecimal dice = BigDecimal.valueOf(random.nextDouble());
        for (var slot : collectionOrderedByPercentage) {
            wonSlot = slot;
            sum = sum.add(slot.getPercentageWining());
            if (sum.compareTo(dice) > 0) break;
        }
        return wonSlot;
    }
}
