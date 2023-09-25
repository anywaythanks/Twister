package com.github.anywaythanks.twisterresource.services.utils;

import com.github.anywaythanks.twisterresource.exceptions.InvalidItemTypeException;
import com.github.anywaythanks.twisterresource.models.CaseSlot;
import com.github.anywaythanks.twisterresource.models.Item;
import com.github.anywaythanks.twisterresource.models.ItemTrash;
import com.github.anywaythanks.twisterresource.models.Slot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlotUtilsTest {
    Item item1;
    Item item2;
    Slot<Item> slot;
    SlotUtils slotUtils;

    @BeforeEach
    void initItems() {
        item1 = ItemTrash.builder()
                .name("1")
                .build();
        item2 = ItemTrash.builder()
                .name("2")
                .build();

        slotUtils = new SlotUtils();
    }

    @Nested
    class Add {
        @Test
        void oneItem() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            slotUtils.addItems(slot, item1, 0);
        }

        @Test
        void twoItem() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            InvalidItemTypeException exception = assertThrows(InvalidItemTypeException.class,
                    () -> slotUtils.addItems(slot, item2, 0));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void negQuantity() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            assertThrows(IllegalArgumentException.class, () -> slotUtils.addItems(slot, item1, -1));
        }

        @Test
        void addItems() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(123)
                    .build();
            slotUtils.addItems(slot, item1, 23);
            assertEquals(slot.getQuantityItem(), 146);
        }
    }

    @Nested
    class Remove {
        @Test
        void oneItem() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            slotUtils.removeItems(slot, item1, 0);
        }

        @Test
        void twoItem() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            InvalidItemTypeException exception = assertThrows(InvalidItemTypeException.class,
                    () ->slotUtils.removeItems(slot, item2, 0));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void negQuantity() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(0)
                    .build();
            assertThrows(IllegalArgumentException.class, () -> slotUtils.removeItems(slot, item1, -1));
        }

        @Test
        void removeItems() {
            slot = CaseSlot.builder()
                    .item(item1)
                    .quantityItem(123)
                    .build();
            slotUtils.removeItems(slot, item1, 23);
            assertEquals(slot.getQuantityItem(), 100);
        }
    }
}