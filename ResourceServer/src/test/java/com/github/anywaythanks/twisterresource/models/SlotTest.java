package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlotTest {
    Item item1;
    Item item2;

    @BeforeEach
    void initItems() {
        item1 = ItemTrash.builder()
                .name("1")
                .build();
        item2 = ItemTrash.builder()
                .name("2")
                .build();
    }

    @Nested
    class Add {
        @Test
        void oneItem() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            slot.addItems(item1, 0);
        }

        @Test
        void twoItem() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            ItemNotTypeException exception = assertThrows(ItemNotTypeException.class,
                    () -> slot.addItems(item2, 0));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void negQuantity() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            assertThrows(IllegalArgumentException.class, () -> slot.addItems(item1, -1));
        }

        @Test
        void addItems() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 123;
            }};
            slot.addItems(item1, 23);
            assertEquals(slot.getQuantityItem(), 146);
        }
    }

    @Nested
    class Remove {
        @Test
        void oneItem() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            slot.removeItems(item1, 0);
        }

        @Test
        void twoItem() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            ItemNotTypeException exception = assertThrows(ItemNotTypeException.class,
                    () -> slot.removeItems(item2, 0));
            assertEquals(exception.getMessage(), "Invalid type specified.");
        }

        @Test
        void negQuantity() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 0;
            }};
            assertThrows(IllegalArgumentException.class, () -> slot.removeItems(item1, -1));
        }

        @Test
        void removeItems() {
            Slot<Item> slot = new Slot<>() {{
                item = item1;
                quantityItem = 123;
            }};
            slot.removeItems(item1, 23);
            assertEquals(slot.getQuantityItem(), 100);
        }
    }
}