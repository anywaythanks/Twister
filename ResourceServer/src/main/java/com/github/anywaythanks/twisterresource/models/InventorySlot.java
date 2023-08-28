package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "inventory_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InventorySlot<T extends Item> extends Slot<T> {
    public InventorySlot(@NonNull T item, @NonNull Integer quantityItem) {
        super(item, quantityItem);
    }
}
