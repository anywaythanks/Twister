package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.MoneyDTO;
import com.github.anywaythanks.twisterresource.models.dto.mapper.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MoneyTypeInformationServiceImpl implements MoneyTypeInformationService {
    private final MoneyTypeRepository typeMoneyRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyTypeInformationServiceImpl(MoneyTypeRepository moneyTypeRepository,
                                           MoneyTypeMapper moneyTypeMapper) {
        this.typeMoneyRepository = moneyTypeRepository;

        this.moneyTypeMapper = moneyTypeMapper;
    }

    public List<MoneyDTO.Type.Response.Partial> listPartial() {
        return typeMoneyRepository.findAll().stream().map(moneyTypeMapper::toPartialDTO).toList();
    }

    public MoneyDTO.Type.Response.Partial getPartial(MoneyDTO.Type.Request.Name name) {
        return moneyTypeMapper.toPartialDTO(typeMoneyRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public MoneyDTO.Type.Response.Id getId(MoneyDTO.Type.Request.Name name) {
        return moneyTypeMapper.toIdDTO(typeMoneyRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }
}
