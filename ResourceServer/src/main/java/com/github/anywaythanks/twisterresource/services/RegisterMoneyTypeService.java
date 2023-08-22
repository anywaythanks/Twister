package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterMoneyTypeService {
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    public RegisterMoneyTypeService(MoneyTypeRepository moneyTypeRepository,
                                    MoneyTypeMapper moneyTypeMapper) {
        this.moneyTypeRepository = moneyTypeRepository;
        this.moneyTypeMapper = moneyTypeMapper;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    public MoneyDTO.Type.Response.Partial merge(MoneyDTO.Type.Request.Name name, MoneyDTO.Type.Request.Create create) {
        var typeMoney = moneyTypeMapper.toType(name, create);
        var pTypeMoney = moneyTypeRepository.findByName(name.getName())
                .orElse(typeMoney);
        pTypeMoney.setPathToIcon(typeMoney.getPathToIcon());
        return moneyTypeMapper.toPartialDTO(moneyTypeRepository.save(pTypeMoney));
    }
}
