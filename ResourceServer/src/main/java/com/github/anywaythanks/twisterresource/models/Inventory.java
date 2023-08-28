package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "inventories")
@NamedEntityGraph(name = "Inventory.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class Inventory {
    @Id
    @GeneratedValue
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    @NonNull
    InventoryName name;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "inventory_slot_mapping",
            joinColumns = {@JoinColumn(name = "item_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "slot_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "item")
    @NonNull
    Map<Item, InventorySlot<?>> inventorySlotMap = new HashMap<>();
}
