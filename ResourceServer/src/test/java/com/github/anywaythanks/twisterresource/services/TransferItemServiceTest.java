package com.github.anywaythanks.twisterresource.services;


import com.github.anywaythanks.twisterresource.config.MapstructConfig;
import com.github.anywaythanks.twisterresource.mappers.GeneralAccountMapper;
import com.github.anywaythanks.twisterresource.mappers.InventoryMapper;
import com.github.anywaythanks.twisterresource.mappers.ItemMapper;
import com.github.anywaythanks.twisterresource.mappers.SlotMapper;
import com.github.anywaythanks.twisterresource.models.*;
import com.github.anywaythanks.twisterresource.models.dto.general.GeneralAccountNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryDebitResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryIdDto;
import com.github.anywaythanks.twisterresource.models.dto.inventory.InventoryNameRequestDto;
import com.github.anywaythanks.twisterresource.models.dto.item.ItemIdDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotActionDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.InventorySlotFullDto;
import com.github.anywaythanks.twisterresource.models.dto.slot.SlotActionDto;
import com.github.anywaythanks.twisterresource.services.managers.InventoryInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotInformationService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotMergeService;
import com.github.anywaythanks.twisterresource.services.managers.InventorySlotPutService;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringJUnitConfig(MapstructConfig.class)
class TransferItemServiceTest {
    @Autowired
    @Spy
    ItemMapper itemMapper;
    @Autowired
    InventoryMapper inventoryMapper;
    @Autowired
    GeneralAccountMapper generalAccountMapper;
    @Autowired
    @Spy
    SlotMapper slotMapper;
    @Mock
    InventoryInformationService inventoryInformationService;
    @Mock
    InventorySlotPutService inventorySlotPutService;
    @Mock
    InventorySlotInformationService inventorySlotInformationService;
    @Mock
    InventorySlotMergeService inventorySlotMergeService;
    TransferItemService transferItemService;
    InventorySlot<Item> inventorySlot;
    Clock clock = Clock.systemUTC();

    void initSlot() {
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
        InventoryName inventoryName = InventoryName.builder()
                .name("i4")
                .build();
        Inventory inventory = Inventory.builder()
                .id(4L)
                .name(inventoryName)
                .generalAccount(generalAccount)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        MoneyType moneyType = MoneyType.builder()
                .id(123)
                .pathToIcon("/")
                .name("123")
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .build();
        Money cost = Money.builder()
                .moneyType(moneyType)
                .value(BigDecimal.valueOf(815))
                .build();
        Item item = ItemMoney.builder()
                .id(3L)
                .name("3")
                .cost(cost)
                .createdOn(Instant.now(clock))
                .modifiedBy(Instant.now(clock))
                .visibleName("vn3")
                .build();
        inventorySlot = InventorySlot.builder()
                .id(9L)
                .inventory(inventory)
                .item(item)
                .quantityItem(9)
                .build();
    }


    void initMocks() {
        ItemIdDto itemId = itemMapper.toIdDTO(inventorySlot.getItem());
        InventoryIdDto inventoryId = inventoryMapper.toIdDTO(inventorySlot.getInventory());
        InventorySlotFullDto slotFull = slotMapper.toInventoryFull(inventorySlot);

        when(inventorySlotInformationService.getFull(inventoryId, itemId)).thenReturn(slotFull);
        transferItemService = new TransferItemService(itemMapper, inventoryInformationService, inventorySlotPutService, slotMapper, inventorySlotInformationService, inventorySlotMergeService);
    }

    @BeforeEach
    void init() {
        initSlot();
        initMocks();
    }

    @Nested
    class Add {
        @Test
        void negateAdd() {
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventorySlot.getInventory().getName());
            SlotActionDto addition = slotMapper.toAction(inventorySlot).withQuantity(-3);
            InventoryDebitResponseDto debit = inventoryMapper.toDebitDTO(inventorySlot.getInventory());

            when(inventoryInformationService.getDebit(inventoryName)).thenReturn(debit);

            assertThrows(IllegalArgumentException.class, () -> transferItemService.add(inventoryName, addition));
        }

        @Test
        void add() {
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventorySlot.getInventory().getName());
            SlotActionDto addition = slotMapper.toAction(inventorySlot).withQuantity(3);
            InventoryDebitResponseDto debit = inventoryMapper.toDebitDTO(inventorySlot.getInventory());
            InventoryIdDto inventoryId = inventoryMapper.toIdDTO(inventorySlot.getInventory());
            SlotActionDto slotPut = slotMapper.toAction(inventorySlot).withQuantity(0);
            InventorySlotActionDto inventorySlotPut = slotMapper.toInventoryAction(inventoryId, slotPut);

            when(inventoryInformationService.getDebit(inventoryName)).thenReturn(debit);

            transferItemService.add(inventoryName, addition);

            InOrder save = inOrder(inventorySlotPutService, inventorySlotMergeService);
            save.verify(inventorySlotPutService, times(1)).putIfAbsent(slotMapper.toPut(inventorySlotPut));
            inventorySlot.setQuantityItem(12);
            save.verify(inventorySlotMergeService, times(1)).merge(slotMapper.toInventoryFull(inventorySlot));
        }
    }

    @Nested
    class Remove {
        @Test
        void negateRemove() {
            GeneralAccountNameRequestDto accountName =
                    generalAccountMapper.toNameRequest(inventorySlot.getInventory().getGeneralAccount().getName());
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventorySlot.getInventory().getName());
            SlotActionDto removed = slotMapper.toAction(inventorySlot).withQuantity(-3);
            InventoryIdDto inventoryId = inventoryMapper.toIdDTO(inventorySlot.getInventory());

            when(inventoryInformationService.getInventoryId(accountName, inventoryName)).thenReturn(inventoryId);

            assertThrows(IllegalArgumentException.class, () -> transferItemService.remove(accountName, inventoryName, removed));
        }

        @Test
        void remove() {
            GeneralAccountNameRequestDto accountName =
                    generalAccountMapper.toNameRequest(inventorySlot.getInventory().getGeneralAccount().getName());
            InventoryNameRequestDto inventoryName = inventoryMapper.toNameRequest(inventorySlot.getInventory().getName());
            SlotActionDto removed = slotMapper.toAction(inventorySlot).withQuantity(3);
            InventoryIdDto inventoryId = inventoryMapper.toIdDTO(inventorySlot.getInventory());

            when(inventoryInformationService.getInventoryId(accountName, inventoryName)).thenReturn(inventoryId);

            transferItemService.remove(accountName, inventoryName, removed);

            SlotActionDto slotPut = slotMapper.toAction(inventorySlot).withQuantity(0);
            InventorySlotActionDto inventorySlotPut = slotMapper.toInventoryAction(inventoryId, slotPut);
            InOrder save = inOrder(inventorySlotPutService, inventorySlotMergeService);
            save.verify(inventorySlotPutService, times(1)).putIfAbsent(slotMapper.toPut(inventorySlotPut));
            inventorySlot.setQuantityItem(6);
            save.verify(inventorySlotMergeService, times(1)).merge(slotMapper.toInventoryFull(inventorySlot));
        }
    }
}