package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseRegisterDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class CaseRegisterService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyMapper moneyMapper;
    private final CaseSlotRegisterService caseSlotRegisterService;
    private final Clock clock;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialResponseDto register(CaseRegisterDto caseRegisterDto) {
        Instant now = Instant.now(clock);
        Money price = moneyMapper.toMoney(caseRegisterDto.getPrice());
        Case newCase = Case.builder()
                .name(caseRegisterDto.getName())
                .visibleName(caseRegisterDto.getVisibleName())
                .description(caseRegisterDto.getDescription())
                .price(price)
                .cooldown(caseRegisterDto.getCooldown())
                .createdOn(now)
                .modifiedBy(now)
                .build();
        Case result = caseRepository.save(newCase);
        caseSlotRegisterService.register(caseMapper.toCaseId(result), caseRegisterDto.getItems());
        return caseMapper.toPartialDTO(result);
    }
}