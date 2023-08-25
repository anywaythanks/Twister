package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory_slots")
public class InventorySlot<T extends Item> extends Slot<T> {
    protected InventorySlot() {

    }

    public InventorySlot(T item, Integer quantityItem) {
        super(item, quantityItem);
    }
}
