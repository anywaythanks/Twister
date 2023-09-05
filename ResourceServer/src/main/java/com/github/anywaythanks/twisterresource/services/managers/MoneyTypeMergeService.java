package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@MergeService
@RequiredArgsConstructor
public class MoneyTypeMergeService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public MoneyTypePartialResponseDto merge(MoneyTypeFullDto fullDto) {
        MoneyType moneyType = moneyTypeMapper.toType(fullDto);
        moneyType.setModifiedBy(Instant.now());
        MoneyType resultType = moneyTypeRepository.save(moneyType);
        return moneyTypeMapper.toPartialDTO(resultType);
    }
}
