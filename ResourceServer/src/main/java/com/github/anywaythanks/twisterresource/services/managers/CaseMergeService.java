package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.MergeService;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialItemsResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeIdResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@MergeService
@RequiredArgsConstructor
public class CaseMergeService {
    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialItemsResponseDto merge(CaseNameRequestDto name, CaseCreateRequestDto create) {

        Optional<Case> optionalCase = caseRepository.findByName(name.getName());
        MoneyTypeIdResponseDto moneyTypeId = moneyTypeInformationService.getId(create.getPrice().getType());

        Set<CaseSlot<Item>> slots = create.getItems()
                .stream()
                .map(slot -> {
                    ItemIdDto itemId = itemInformationService.getId(slot.getItem());
                    Item item = itemRepository.findById(itemId.getId())
                            .orElseThrow(NotFoundException::new);
                    return caseMapper.toCaseSlot(item, slot);
                })
                .collect(Collectors.toSet());
        Instant now = Instant.now();
        MoneyTypeFullDto fullType = moneyTypeInformationService.getFull(create.getPrice().getType());
        MoneyFullDto fullMoney = moneyMapper.toFull(fullType, create.getPrice());
        Case mergedCase = new Case(name.getName(), create.getVisibleName(), create.getDescription(), moneyMapper.toMoney(fullMoney),
                create.getCooldown(), now, now);
        //slots TODO register
        optionalCase.ifPresent(persistenceCase -> {
            persistenceCase.getCaseSlotSet().clear();//TODO:DELETE in PUT
            persistenceCase.setCooldown(mergedCase.getCooldown());
            persistenceCase.getCaseSlotSet().addAll(mergedCase.getCaseSlotSet());
            persistenceCase.setPrice(mergedCase.getPrice());
            persistenceCase.setDescription(mergedCase.getDescription());
            persistenceCase.setVisibleName(mergedCase.getVisibleName());
            persistenceCase.setModifiedBy(Instant.now());
        });
        Case resultCase = optionalCase.orElse(mergedCase);
        return caseMapper.toPartialItemsDTO(caseRepository.save(resultCase));
    }
}
