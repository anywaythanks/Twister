package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.PutService;
import com.github.anywaythanks.twisterresource.mappers.CaseMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.Case;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CasePartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.slot.CaseSlotRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.repository.CaseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@PutService
@RequiredArgsConstructor
public class CasePutService {
    private final CaseRepository caseRepository;
    private final CaseMapper caseMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyMapper moneyMapper;
    private final SlotMapper slotMapper;
    private final CaseRegisterService caseRegisterService;
    private final CaseMergeService caseMergeService;
    private final ItemInformationService itemInformationService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public CasePartialResponseDto put(CaseNameRequestDto name, CaseCreateRequestDto create) {
        MoneyTypeFullDto typeFull = moneyTypeInformationService.getFull(create.getPrice().getType());
        MoneyFullDto price = moneyMapper.toFull(typeFull, create.getPrice());
        Optional<Case> optionalCase = caseRepository.findByName(name.getName());
        if (optionalCase.isEmpty()) {
            List<CaseSlotRegisterDto> items = create.getItems()
                    .stream()
                    .map(slot -> {
                        ItemIdDto itemId = itemInformationService.getId(slot.getItem());
                        return slotMapper.toCaseRegister(itemId, slot);
                    })
                    .toList();
            return caseRegisterService.register(caseMapper.toRegister(items, name, price, create));
        }
        Case mergedCase = optionalCase.get();
        mergedCase.setVisibleName(create.getVisibleName());
        mergedCase.setDescription(create.getDescription());
        mergedCase.setPrice(moneyMapper.toMoney(price));
        mergedCase.setCooldown(create.getCooldown());
        return caseMergeService.merge(caseMapper.toFull(mergedCase));
    }
}
