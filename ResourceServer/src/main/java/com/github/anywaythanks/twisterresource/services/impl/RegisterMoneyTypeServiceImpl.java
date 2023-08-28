package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.RegisterMoneyTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterMoneyTypeServiceImpl implements RegisterMoneyTypeService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyTypePartialResponseDto merge(MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto create) {
        var typeMoney = moneyTypeMapper.toType(name, create);
        var pTypeMoney = moneyTypeRepository.findByName(name.getName())
                .orElse(typeMoney);
        pTypeMoney.setPathToIcon(typeMoney.getPathToIcon());
        return moneyTypeMapper.toPartialDTO(moneyTypeRepository.save(pTypeMoney));
    }
}
