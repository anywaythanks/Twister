package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Setter
@Getter
@SuperBuilder
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

    public void addItems(@NonNull Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!this.item.equals(item))
            throw new ItemNotTypeException();
        quantityItem += quantity;
    }

    public void removeItems(@NonNull Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!this.item.equals(item))
            throw new ItemNotTypeException();
        if (quantityItem < quantity)
            throw new RuntimeException();//TODO:throw
        quantityItem -= quantity;
    }
}
