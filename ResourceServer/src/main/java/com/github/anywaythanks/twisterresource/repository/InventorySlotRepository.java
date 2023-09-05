package com.github.anywaythanks.twisterresource.repository;

import com.github.anywaythanks.twisterresource.models.Inventory;
import com.github.anywaythanks.twisterresource.models.InventorySlot;
import com.github.anywaythanks.twisterresource.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventorySlotRepository extends JpaRepository<InventorySlot<?>, Long> {
    Optional<InventorySlot<Item>> findFirstByInventoryAndItem(Inventory inventory, Item item);

    Optional<InventorySlot<Item>> findFirstByInventoryIdAndItemId(Long inventoryId, Long itemId);

    List<InventorySlot<Item>> findAllByInventoryId(Long inventoryId);
}