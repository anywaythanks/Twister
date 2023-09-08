package com.github.anywaythanks.twisterresource.services.managers;

import com.github.anywaythanks.twisterresource.annotation.RegisterService;
import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemMoney;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemMoneyRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemPartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemRegisterDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemTrashRegisterDto;
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
        Item item;
        Instant now = Instant.now(clock);
        if (registerDto instanceof ItemMoneyRegisterDto itemMoney) {
            Money cost = moneyMapper.toMoney(itemMoney.getCost());
            item = ItemMoney.builder()
                    .name(itemMoney.getName())
                    .visibleName(itemMoney.getVisibleName())
                    .createdOn(now)
                    .modifiedBy(now)
                    .cost(cost)
                    .build();
        } else if (registerDto instanceof ItemTrashRegisterDto itemTrash) {
            item = ItemTrash.builder()
                    .name(itemTrash.getName())
                    .visibleName(itemTrash.getVisibleName())
                    .createdOn(now)
                    .modifiedBy(now)
                    .build();
        } else throw new ItemNotTypeException();
        Item resultItem = itemRepository.save(item);
        return itemMapper.toPartialDTO(itemRepository.save(resultItem));
    }
}
