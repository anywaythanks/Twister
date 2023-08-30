package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.MoneyType;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegisterCaseService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyTypeRepository moneyTypeRepository;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final ItemRepository itemRepository;
    private final ItemInformationService itemInformationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialResponseDto merge(CaseNameRequestDto name, CaseCreateRequestDto create) {
        Optional<Case> optionalCase = caseRepository.findByName(name.getName());
        MoneyTypeIdResponseDto moneyTypeId = moneyTypeInformationService.getId(create.getPrice().getType());
        MoneyType type = moneyTypeRepository.findById(moneyTypeId.getId())
                .orElseThrow(NotFoundException::new);
        Set<CaseSlot<Item>> slots = create.getItems()
                .stream()
                .map(slot -> {
                    ItemIdResponseDto itemId = itemInformationService.getId(slot.getItem());
                    Item item = itemRepository.findById(itemId.getId())
                            .orElseThrow(NotFoundException::new);
                    return caseMapper.toCaseSlot(item, slot);
                })
                .collect(Collectors.toSet());
        Case mergedCase = caseMapper.toCase(slots, name, type, create);
        optionalCase.ifPresent(persistenceCase -> {
            persistenceCase.getCaseSlotSet().clear();//TODO:DELETE in PUT
            persistenceCase.setCooldown(mergedCase.getCooldown());
            persistenceCase.getCaseSlotSet().addAll(mergedCase.getCaseSlotSet());
            persistenceCase.setPrice(mergedCase.getPrice());
            persistenceCase.setDescription(mergedCase.getDescription());
            persistenceCase.setVisibleName(mergedCase.getVisibleName());
        });
        Case resultCase = optionalCase.orElse(mergedCase);
        return caseMapper.toPartialDTO(caseRepository.save(resultCase));
    }
}