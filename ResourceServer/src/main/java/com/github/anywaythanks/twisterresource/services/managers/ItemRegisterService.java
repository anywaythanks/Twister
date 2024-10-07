package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.exceptions.InvalidItemTypeException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.item.*;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;

@RegisterService
@RequiredArgsConstructor
public class ItemRegisterService {
    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final MoneyMapper moneyMapper;
    private final Clock clock;

    @PreAuthorize("hasAuthority('ADMIN')")
    @Transactional
    public ItemPartialResponseDto register(ItemRegisterDto registerDto) {
        Instant now = Instant.now(clock);
        Item item = switch (registerDto) {
            case ItemMoneyRegisterDto itemMoney -> ItemMoney.builder()
                    .name(itemMoney.getName())
                    .visibleName(itemMoney.getVisibleName())
                    .createdOn(now)
                    .modifiedBy(now)
                    .cost(moneyMapper.toMoney(itemMoney.getCost()))
                    .build();
            case ItemTrashRegisterDto itemTrash -> ItemTrash.builder()
                    .name(itemTrash.getName())
                    .visibleName(itemTrash.getVisibleName())
                    .createdOn(now)
                    .modifiedBy(now)
                    .build();
        };
        Item resultItem = itemRepository.save(item);
        return itemMapper.toPartialDTO(itemRepository.save(resultItem));
    }
}
