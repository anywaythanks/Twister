package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.*;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Twist;
import com.github.anywaythanks.twisterresource.models.TwistNumber;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseFullDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountFullDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountIdAndUuidDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.twist.TwistRegisterDto;
import com.github.anywaythanks.twisterresource.repository.TwistNumberRepository;
import com.github.anywaythanks.twisterresource.repository.TwistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class TwistRegisterService {
    private final TwistRepository twistRepository;
    private final TwistNumberRepository twistNumberRepository;
    private final TwistMapper twistMapper;
    private final AccountMapper accountMapper;
    private final GeneralAccountMapper generalAccountMapper;
    private final CaseMapper caseMapper;
    private final SlotMapper slotMapper;
    private final Clock clock;

    @Transactional
    public TwistPartialResponseDto register(TwistRegisterDto registerDto) {
        CaseSlot<Item> wonSlot = slotMapper.toCaseSlot(registerDto.getSlot());
        TwistNumber number = twistMapper.toNumber(registerDto.getNumber());
        Twist<?> resultTwist = Twist.builder()
                .account(accountMapper.toAccount(registerDto.getAccount()))
                .generalAccount(generalAccountMapper.toAccount(registerDto.getGeneralAccount()))
                .number(twistNumberRepository.save(number))
                .twistCase(caseMapper.toCase(registerDto.getSelectCase()))
                .wonItem(wonSlot.getItem())
                .quantityItem(wonSlot.getQuantityItem())
                .createdOn(Instant.now(clock))
                .build();
        return twistMapper.toPartialDTO(wonSlot.getName(), twistRepository.save(resultTwist));
    }

    @Transactional
    public TwistPartialResponseDto register(CaseFullDto selectCaseDto, GeneralAccountIdAndUuidDto generalAccountIdDto,
                                            AccountFullDto accountFullDto, CaseSlotFullDto wonSlotDto) {
        TwistNumber newNumber = twistNumberRepository.save(TwistNumber.builder().build());
        return register(twistMapper.toRegister(newNumber, generalAccountIdDto, wonSlotDto, selectCaseDto, accountFullDto));
    }
}
