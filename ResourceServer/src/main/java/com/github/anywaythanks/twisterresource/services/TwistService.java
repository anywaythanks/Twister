package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.CooldownException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.AccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.CaseDTO;
import com.github.anywaythanks.twisterresource.models.dto.GeneralAccountDTO;
import com.github.anywaythanks.twisterresource.models.dto.TwistDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.*;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.GeneralAccountRepository;
import com.github.anywaythanks.twisterresource.repository.TwistRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Collection;

@Service
@Transactional
public class TwistService {
    private final TransferMoneyService transferMoneyService;
    private final TransferItemService transferItemService;
    private final TwistRepository twistRepository;
    private final GeneralAccountInformationService generalAccountInformationService;
    private final TwistMapper twistMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final GeneralAccountRepository generalAccountRepository;
    private final AccountMapper accountMapper;
    private final SlotMapper slotMapper;
    private final CaseActualInformationService caseActualInformationService;
    private final CaseMapper caseMapper;
    private final MoneyMapper moneyMapper;
    private final CaseRepository caseRepository;

    public TwistService(TransferMoneyService transferMoneyService,
                        TransferItemService transferItemService,
                        TwistRepository twistRepository,
                        GeneralAccountInformationService generalAccountInformationService,
                        TwistMapper twistMapper,
                        GeneralAccountMapper generalAccountMapper,
                        GeneralAccountRepository generalAccountRepository,
                        AccountMapper accountMapper,
                        SlotMapper slotMapper,
                        CaseActualInformationService caseActualInformationService,
                        CaseMapper caseMapper,
                        MoneyMapper moneyMapper,
                        CaseRepository caseRepository) {
        this.transferMoneyService = transferMoneyService;
        this.transferItemService = transferItemService;
        this.twistRepository = twistRepository;
        this.generalAccountInformationService = generalAccountInformationService;
        this.twistMapper = twistMapper;
        this.generalAccountMapper = generalAccountMapper;
        this.generalAccountRepository = generalAccountRepository;
        this.accountMapper = accountMapper;
        this.slotMapper = slotMapper;
        this.caseActualInformationService = caseActualInformationService;
        this.caseMapper = caseMapper;
        this.moneyMapper = moneyMapper;
        this.caseRepository = caseRepository;
    }

    public TwistDTO.Response.Partial twist(GeneralAccountDTO.Request.Name name,
                                           AccountDTO.Request.Number number,
                                           CaseDTO.Request.Name caseName) throws NoSuchAlgorithmException {
        var cid = caseActualInformationService.getCooldownId(name, caseName);
        if (!cid.getCooldown().isZero()) throw new CooldownException();
        var twistedCase = caseRepository.findById(caseMapper.toId(cid)).orElseThrow(NotFoundException::new);
        transferMoneyService.credit(name, number, moneyMapper.toRequest(twistedCase.getPrice()));
        var wonSlot = twist(twistedCase.getCaseSlotSet());
        transferItemService.add(number, slotMapper.toTransfer(wonSlot));
        var generalAccount = generalAccountRepository.findById(
                        generalAccountMapper.toId(generalAccountInformationService.getId(name)))
                .orElseThrow(NotFoundException::new);
        var twist = new Twist<>(generalAccount.getAccounts().get(accountMapper.toNumber(number)),
                twistedCase,
                wonSlot.getItem(),
                wonSlot.getQuantityItem(),
                Instant.now(),
                generalAccount);
        return twistMapper.toDTO(wonSlot, twistRepository.save(twist));
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
