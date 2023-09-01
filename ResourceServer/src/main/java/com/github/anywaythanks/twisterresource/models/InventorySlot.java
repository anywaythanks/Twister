package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "inventory_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class InventorySlot<T extends Item> extends Slot<T> {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @NonNull
    @JoinColumn(name = "inventory_id", insertable = false, updatable = false)
    Inventory inventory;

    public InventorySlot(@NonNull T item, @NonNull Integer quantityItem, @NonNull Inventory inventory) {
        super(item, quantityItem);
        this.inventory = inventory;
    }
}
