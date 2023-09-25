package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
@EqualsAndHashCode
public abstract class Slot<T extends Item> {
    @Id
    @GeneratedValue(generator = "slot_seq")
    @SequenceGenerator(name = "slot_seq", sequenceName = "slot_id_seq")
    Long id;
    @NotNull
    @ManyToOne(targetEntity = Item.class, optional = false)
    @JoinColumn(name = "item_id", nullable = false)
    T item;
    @NotNull
    @Min(0)
    @Column(name = "quantity_item", nullable = false)
    Integer quantityItem;
}
