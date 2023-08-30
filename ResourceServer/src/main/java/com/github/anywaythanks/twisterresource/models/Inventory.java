package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    @JoinColumn(name = "inventory_id", nullable = false)
    @NonNull
    Set<InventorySlot<?>> slots = new HashSet<>();
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @NonNull
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    @NonNull
    Instant createdOn;
    @NotNull
    @ManyToOne
    @NonNull
    @JoinColumn(name = "general_account_id", insertable = false, updatable = false)
    @Getter(AccessLevel.NONE)
    GeneralAccount generalAccount;
}
