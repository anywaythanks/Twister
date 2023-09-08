package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeRegisterDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class MoneyTypeRegisterService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;
    private final Clock clock;
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public MoneyTypePartialResponseDto register(MoneyTypeRegisterDto registerDto) {
        Instant now = Instant.now(clock);
        MoneyType moneyType = MoneyType.builder()
                .name(registerDto.getName())
                .pathToIcon(registerDto.getPathToIcon())
                .createdOn(now)
                .modifiedBy(now)
                .build();
        MoneyType resultType = moneyTypeRepository.save(moneyType);
        return moneyTypeMapper.toPartialDTO(resultType);
    }
}
