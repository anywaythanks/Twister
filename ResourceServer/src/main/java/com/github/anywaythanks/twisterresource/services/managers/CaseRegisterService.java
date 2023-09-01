package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.acase.*;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RegisterService
@RequiredArgsConstructor
public class CaseRegisterService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final ItemRepository itemRepository;
    private final ItemInformationService itemInformationService;
    private final MoneyMapper moneyMapper;
    private final CaseSlotRegisterService caseSlotRegisterService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialResponseDto register(CaseRegisterDto caseRegisterDto) {
        Instant now = Instant.now();
        Money price = moneyMapper.toMoney(caseRegisterDto.getPrice());
        Case newCase = new Case(caseRegisterDto.getName(),
                caseRegisterDto.getVisibleName(),
                caseRegisterDto.getDescription(),
                price,
                caseRegisterDto.getCooldown(),
                now, now);
        Case result = caseRepository.save(newCase);
        caseSlotRegisterService.register(caseRegisterDto.getItems());
        return caseMapper.toPartialDTO(result);
    }
}