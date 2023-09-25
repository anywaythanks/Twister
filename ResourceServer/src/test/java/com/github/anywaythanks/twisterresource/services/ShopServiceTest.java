package com.github.anywaythanks.twisterresource.services;

import com.github.anywaythanks.twisterresource.config.MapstructConfig;
import com.github.anywaythanks.twisterresource.exceptions.NoSellingItemException;
import com.github.anywaythanks.twisterresource.mappers.*;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.account.AccountNumberRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemFullDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyCreateRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeFullDto;
import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotActionDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotQuantityRequestDto;
import com.github.anywaythanks.twisterresource.models.interfaces.SellingItem;
import com.github.anywaythanks.twisterresource.services.managers.ItemInformationService;
import com.github.anywaythanks.twisterresource.services.managers.MoneyTypeInformationService;
import com.github.anywaythanks.twisterresource.services.utils.MoneyUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(MapstructConfig.class)
class ShopServiceTest {
    @Autowired
    @Spy
    ItemMapper itemMapper;
    @Autowired
    GeneralAccountMapper generalAccountMapper;
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    MoneyTypeMapper moneyTypeMapper;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    @Spy
    SlotMapper slotMapper;
    @Autowired
    @Spy
    MoneyMapper moneyMapper;
    @Mock
    TransferMoneyService transferMoneyService;
    @Mock
    TransferItemService transferItemService;
    @Mock
    MoneyTypeInformationService moneyTypeInformationService;
    @Mock
    ItemInformationService itemInformationService;
    @Mock
    MoneyUtils moneyUtils;
    Item noSellingItem;
    Account account;
    MoneyType type;
    ShopService shopService;
    SellItem<?> sellingItem;
    Inventory inventory;
    Clock clock = Clock.systemUTC();

    private record SellItem<T extends Item & SellingItem>(T item) {

    }

    void initData() {
        GeneralAccountName generalAccountName = GeneralAccountName.builder()
                .name("g5")
                .build();
        GeneralAccount generalAccount = GeneralAccount.builder()
                .id(5L)
                .userUuid("faaa340c-a418-4ae6-b5eb-c18976d7b171")
                .name(generalAccountName)
                .nickname("n5")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        type = MoneyType.builder()
                .id(123)
                .pathToIcon("/45")
                .name("123")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        Money cost = Money.builder()
                .moneyType(type)
                .value(BigDecimal.valueOf(15))
                .build();
        ItemMoney moneyItem = ItemMoney.builder()
                .id(3L)
                .name("3")
                .cost(cost)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .visibleName("vn3")
                .build();
        sellingItem = new SellItem<>(moneyItem);
        noSellingItem = ItemTrash.builder()
                .id(7L)
                .name("567")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .visibleName("vn7")
                .build();
        InventoryName inventoryName = InventoryName.builder()
                .name("i4")
                .build();
        inventory = Inventory.builder()
                .id(4L)
                .name(inventoryName)
                .generalAccount(generalAccount)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        Money amount = Money.builder()
                .moneyType(type)
                .value(BigDecimal.valueOf(815))
                .build();
        AccountNumber accountNumber = AccountNumber.builder()
                .number("54312")
                .build();
        account = Account.builder()
                .id(467L)
                .generalAccount(generalAccount)
                .number(accountNumber)
                .amount(amount)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
    }

    void initMocks() {
        shopService = new ShopService(transferMoneyService, transferItemService, moneyMapper, slotMapper, moneyTypeInformationService, itemInformationService, itemMapper, moneyUtils);
    }

    @BeforeEach
    void init() {
        initData();
        initMocks();
    }

    @Nested
    class Sell {
        @Test
        void sellNoSelling() {
            ItemNameRequestDto itemName = itemMapper.toNameDTO(noSellingItem);
            ItemFullDto itemFull = itemMapper.toFullItem(noSellingItem);
            when(itemInformationService.getFull(itemName)).thenReturn(itemFull);

            GeneralAccountNameRequestDto generalName = generalAccountMapper
                    .toNameRequest(account.getGeneralAccount().getName());
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventory.getName());
            AccountNumberRequestDto accountNumber = accountMapper.toNumberRequest(account.getNumber());


            NoSellingItemException exception = assertThrows(NoSellingItemException.class,
                    () -> shopService.sell(generalName, inventoryName, itemName, accountNumber, new SlotQuantityRequestDto(4)));
            assertEquals(exception.getMessage(), "The item is not for sale.");
        }

        @Test
        void sell() {
            ItemNameRequestDto itemName = itemMapper.toNameDTO(sellingItem.item());
            ItemFullDto itemFull = itemMapper.toFullItem(sellingItem.item());
            Money multiplied = Money.builder().moneyType(type).value(BigDecimal.valueOf(60)).build();
            when(itemInformationService.getFull(itemName)).thenReturn(itemFull);
            when(moneyUtils.multiply(sellingItem.item().getCost(), BigDecimal.valueOf(4))).thenReturn(multiplied);

            GeneralAccountNameRequestDto generalName = generalAccountMapper
                    .toNameRequest(account.getGeneralAccount().getName());
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventory.getName());
            AccountNumberRequestDto accountNumber = accountMapper.toNumberRequest(account.getNumber());
            shopService.sell(generalName, inventoryName, itemName, accountNumber, new SlotQuantityRequestDto(4));

            MoneyFullDto resultDto = moneyMapper.toFull(multiplied);
            SlotActionDto resultSlot = slotMapper.toAction(itemFull, new SlotQuantityRequestDto(4));
            InOrder order = inOrder(transferItemService, transferMoneyService);
            order.verify(transferItemService, times(1)).remove(generalName, inventoryName, resultSlot);
            order.verify(transferMoneyService, times(1)).debit(accountNumber, resultDto);
        }
    }

    @Nested
    class Buy {
        @Test
        void buy() {
            ItemFullDto itemFull = itemMapper.toFullItem(sellingItem.item());
            MoneyTypeNameRequestDto nameType = moneyTypeMapper.toName(sellingItem.item().getCost().getMoneyType());
            MoneyTypeFullDto typeFull = moneyTypeMapper.toFullDto(sellingItem.item().getCost().getMoneyType());
            when(moneyTypeInformationService.getFull(nameType)).thenReturn(typeFull);

            GeneralAccountNameRequestDto generalName = generalAccountMapper
                    .toNameRequest(account.getGeneralAccount().getName());
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventory.getName());
            AccountNumberRequestDto accountNumber = accountMapper.toNumberRequest(account.getNumber());
            SlotActionDto bought = slotMapper.toAction(itemFull, new SlotQuantityRequestDto(4));
            Money price = Money.builder().moneyType(type).value(BigDecimal.valueOf(60)).build();

            MoneyCreateRequestDto createPrice = moneyMapper.toRequest(price);
            shopService.buy(generalName, inventoryName, bought, accountNumber, createPrice);

            MoneyFullDto priceDto = moneyMapper.toFull(price);
            InOrder order = inOrder(transferMoneyService, transferItemService);
            order.verify(transferMoneyService, times(1)).credit(generalName, accountNumber, priceDto);
            order.verify(transferItemService, times(1)).add(inventoryName, bought);
        }
    }
}