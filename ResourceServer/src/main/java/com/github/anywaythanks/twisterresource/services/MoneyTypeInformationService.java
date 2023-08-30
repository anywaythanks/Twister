package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MoneyTypeInformationService {
    private final MoneyTypeRepository typeMoneyRepository;
    private final MoneyTypeMapper moneyTypeMapper;

    public MoneyTypePartialResponseDto getPartial(MoneyTypeNameRequestDto name) {
        return moneyTypeMapper.toPartialDTO(typeMoneyRepository.findByName(name.getName())
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
