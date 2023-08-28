package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import com.github.anywaythanks.twisterresource.repository.MoneyTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RegisterItemService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final MoneyTypeRepository moneyTypeRepository;
    @PreAuthorize("hasAuthority('ADMIN')")
    public ItemPartialResponseDto merge(ItemNameRequestDto name, ItemCreateRequestDto mergeItem) {
        var item = switch (mergeItem.getType()) {
            case MONEY -> {
                if (mergeItem instanceof ItemMoneyCreateRequestDto itemMoney) {
                    var type = moneyTypeRepository.findById(moneyTypeInformationService.getId(itemMoney.getCost().getType()).getId())
                            .orElseThrow(NotFoundException::new);
                    yield itemMapper.toItemMoney(name, type, itemMoney);
                }
                throw new ItemNotTypeException();
            }
            case TRASH -> {
                if (mergeItem instanceof ItemTrashCreateRequestDto itemTrash)
                    yield itemMapper.toItemTrash(name, itemTrash);
                throw new ItemNotTypeException();
            }
        };
        itemRepository.findByName(name.getName()).ifPresent(it -> {
            if (it.getClass().isInstance(item)) throw new ItemNotTypeException();
        });
        return itemMapper.toPartialDTO(itemRepository.save(item));
    }
}
