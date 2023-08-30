package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterMoneyTypeService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public MoneyTypePartialResponseDto merge(MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto create) {
        MoneyType mergedType = moneyTypeMapper.toType(name, create);
        MoneyType resultType = moneyTypeRepository.findByName(name.getName())
                .orElse(mergedType);
        resultType.setPathToIcon(mergedType.getPathToIcon());
        return moneyTypeMapper.toPartialDTO(moneyTypeRepository.save(resultType));
    }
}
