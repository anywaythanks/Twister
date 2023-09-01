package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.InformationService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@InformationService
@RequiredArgsConstructor
public class MoneyTypeInformationService {
    private final MoneyTypeRepository typeMoneyRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyTypePartialResponseDto getPartial(MoneyTypeNameRequestDto name) {
        return moneyTypeMapper.toPartialDTO(typeMoneyRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public MoneyTypeFullDto getFull(MoneyTypeNameRequestDto name) {
        return moneyTypeMapper.toFullDto(typeMoneyRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public MoneyTypeIdResponseDto getId(MoneyTypeNameRequestDto name) {
        return moneyTypeMapper.toIdDTO(typeMoneyRepository.findByName(name.getName())
                .orElseThrow(NotFoundException::new));
    }

    public List<MoneyTypePartialResponseDto> getPartials() {
        return typeMoneyRepository
                .findAll()
                .stream()
                .map(moneyTypeMapper::toPartialDTO)
                .toList();
    }
}
