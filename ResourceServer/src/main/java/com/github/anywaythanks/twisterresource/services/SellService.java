package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.exceptions.NoSellingItemException;
import com.github.anywaythanks.twisterresource.exceptions.NotFoundException;
import com.github.anywaythanks.twisterresource.mappers.MoneyMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.interfaces.SellingItem;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
@RequiredArgsConstructor
public class SellService {
    private final TransferMoneyService transferMoneyService;
    private final TransferItemService transferItemService;
    private final MoneyMapper moneyMapper;
    private final SlotMapper slotMapper;
    private final InventoryInformationService inventoryInformationService;
    private final ItemRepository itemRepository;

    public void sell(GeneralAccountNameRequestDto name,
                     InventoryNameRequestDto nameInventory,
                     ItemNameRequestDto nameItem,
                     AccountNumberRequestDto number,
                     SlotQuantityRequestDto quantity) {
        var slot = inventoryInformationService.getSlotId(name, nameInventory, nameItem);
        var item = itemRepository.findById(slot.getItem().getId()).orElseThrow(NotFoundException::new);
        if (item instanceof SellingItem sellingItem) {
            transferItemService.remove(name, nameInventory, slotMapper.toTransfer(quantity, slot));
            transferMoneyService.debit(number, moneyMapper.toRequest(sellingItem.getCost().multiply(BigDecimal.valueOf(quantity.getQuantity()))));
        } else throw new NoSellingItemException();
    }
}
