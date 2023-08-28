package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterCaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterCaseServiceImpl implements RegisterCaseService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final ItemRepository itemRepository;
    private final ItemInformationService itemInformationService;

    public CasePartialResponseDto merge(CaseNameRequestDto name, CaseCreateRequestDto create) {
        var oCase = caseRepository.findByName(name.getName());
        var type = moneyTypeRepository.findById(moneyTypeInformationService.
                getId(create.getPrice().getType()).getId()).orElseThrow(NotFoundException::new);
        var slots = create.getItems().stream().map(s -> {
            final var item = itemRepository.findById(itemInformationService.getId(s.getItem()).getId())
                    .orElseThrow(NotFoundException::new);
            return caseMapper.toCaseSlot(item, s);
        }).collect(Collectors.toSet());
        var pCase = caseMapper.toCase(slots, name, type, create);
        oCase.ifPresent(rCase -> {
            rCase.getCaseSlotSet().clear();//TODO:DEL IN PUT
            rCase.setCooldown(pCase.getCooldown());
            rCase.getCaseSlotSet().addAll(pCase.getCaseSlotSet());
            rCase.setPrice(pCase.getPrice());
            rCase.setDescription(pCase.getDescription());
            rCase.setVisibleName(pCase.getVisibleName());
        });
        return caseMapper.toPartialDTO(caseRepository.save(oCase.orElse(pCase)));
    }
}