package com.github.anywaythanks.twisterresource.services.managers;

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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MoneyTypePutService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;
    private final MoneyTypeRegisterService moneyTypeRegisterService;
    private final MoneyTypeMergeService moneyTypeMergeService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public MoneyTypePartialResponseDto put(MoneyTypeNameRequestDto name, MoneyTypeCreateRequestDto create) {
        Optional<MoneyType> optionalMoneyType = moneyTypeRepository.findByName(name.getName());
        if (optionalMoneyType.isEmpty()) {
            return moneyTypeRegisterService.register(moneyTypeMapper.toRegister(name, create));
        }
        MoneyType moneyType = optionalMoneyType.get();
        moneyType.setPathToIcon(create.getPathToIcon());
        return moneyTypeMergeService.merge(moneyTypeMapper.toFullDto(moneyType));
    }
}
