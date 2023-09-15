package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "inventories")
@NamedEntityGraph(name = "Inventory.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
public class Inventory {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "inventory_id_seq")
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    InventoryName name;
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "general_account_id", nullable = false)
    GeneralAccount generalAccount;
}
