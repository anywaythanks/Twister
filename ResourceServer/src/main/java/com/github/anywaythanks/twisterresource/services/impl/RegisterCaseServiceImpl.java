package com.github.anywaythanks.twisterresource.services.impl;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.mappers.MoneyTypeMapper;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import com.github.anywaythanks.twisterresource.services.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.RegisterCaseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class RegisterCaseServiceImpl implements RegisterCaseService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeMapper moneyTypeMapper;
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemInformationService itemInformationService;

    public RegisterCaseServiceImpl(CaseRepository caseRepository,
                                   CaseMapper caseMapper,
                                   MoneyTypeRepository moneyTypeRepository,
                                   MoneyTypeInformationService moneyTypeInformationService,
                                   MoneyTypeMapper moneyTypeMapper,
                                   ItemRepository itemRepository,
                                   ItemMapper itemMapper,
                                   ItemInformationService itemInformationService) {
        this.caseRepository = caseRepository;
        this.caseMapper = caseMapper;
        this.moneyTypeRepository = moneyTypeRepository;
        this.moneyTypeInformationService = moneyTypeInformationService;
        this.moneyTypeMapper = moneyTypeMapper;
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
        this.itemInformationService = itemInformationService;
    }

    public CasePartialResponseDto merge(CaseNameRequestDto name, CaseCreateRequestDto create) {
        var oCase = caseRepository.findByName(name.getName());
        var type = moneyTypeRepository.findById(moneyTypeMapper.toId(moneyTypeInformationService.
                getId(create.getPrice().getType()))).orElseThrow(NotFoundException::new);
        var slots = create.getItems().stream().map(s -> {
            final var item = itemRepository.findById(itemMapper.toId(itemInformationService.getId(s.getItem())))
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