package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "inventory_slots")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class InventorySlot<T extends Item> extends Slot<T> {
    public InventorySlot(@NonNull T item,@NonNull Integer quantityItem, @NonNull Inventory inventory) {
        super(item, quantityItem);
        this.inventory = inventory;
    }

    @NotNull
    @ManyToOne
    @NonNull
    @JoinColumn(name = "inventory_id", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    Inventory inventory;
}
