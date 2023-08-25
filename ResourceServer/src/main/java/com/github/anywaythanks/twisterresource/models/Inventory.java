package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "inventories")
@NamedEntityGraph(name = "Inventory.detail",
        attributeNodes = @NamedAttributeNode("name"))
public class Inventory {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    InventoryName name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "inventory_slot_mapping",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "slot_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item")
    Map<Item, InventorySlot<?>> inventorySlotMap = new HashMap<>();

    protected Inventory() {
    }

    public Inventory(InventoryName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public InventoryName getName() {
        return name;
    }

    public Map<Item, InventorySlot<?>> getInventorySlotMap() {
        return inventorySlotMap;
    }
}
