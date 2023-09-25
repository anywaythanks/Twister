package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NoSellingItemException;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.Money;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotActionDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.models.interfaces.SellingItem;
import com.github.anywaythanks.twisterresource.services.managers.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.utils.MoneyUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ShopService {
    private final TransferMoneyService transferMoneyService;
    private final TransferItemService transferItemService;
    private final MoneyMapper moneyMapper;
    private final SlotMapper slotMapper;
    private final MoneyTypeInformationService moneyTypeInformationService;
    private final ItemInformationService itemInformationService;
    private final ItemMapper itemMapper;

    private final MoneyUtils moneyUtils;

    @Transactional
    public void sell(GeneralAccountNameRequestDto name,
                     InventoryNameRequestDto nameInventory,
                     ItemNameRequestDto nameItem,
                     AccountNumberRequestDto number,
                     SlotQuantityRequestDto quantity) {
        ItemFullDto fullDto = itemInformationService.getFull(nameItem);
        Item item = itemMapper.toItem(fullDto);
        if (item instanceof SellingItem sellingItem) {
            Money costAllItems = moneyUtils.multiply(sellingItem.getCost(), BigDecimal.valueOf(quantity.getQuantity()));
            transferItemService.remove(name, nameInventory, slotMapper.toAction(fullDto, quantity));
            transferMoneyService.debit(number, moneyMapper.toFull(costAllItems));
        } else throw new NoSellingItemException();
    }

    @Transactional
    public void buy(GeneralAccountNameRequestDto name,
                    InventoryNameRequestDto nameInventory,
                    SlotActionDto bought,
                    AccountNumberRequestDto number,
                    MoneyCreateRequestDto price) {
        MoneyTypeFullDto typeFull = moneyTypeInformationService.getFull(price.getType());
        MoneyFullDto moneyFull = moneyMapper.toFull(typeFull, price);
        transferMoneyService.credit(name, number, moneyFull);
        transferItemService.add(nameInventory, bought);
    }
}
