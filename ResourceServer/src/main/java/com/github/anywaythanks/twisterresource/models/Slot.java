package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Slot<T extends Item> {
    @Id
    @GeneratedValue(generator = "SLOT_ID_GENERATOR")
    Long id;
    @NotNull
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    T item;
    @NotNull
    @Min(0)
    @Column(name = "quantity_item", nullable = false)
    Integer quantityItem;

    protected Slot() {
    }

    public Slot(T item, Integer quantityItem) {
        this.item = item;
        this.quantityItem = 0;
        addItems(item, quantityItem);
    }

    public Integer getQuantityItem() {
        return quantityItem;
    }

    public void addItems(Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!item.getClass().isInstance(this.item))
            throw new ItemNotTypeException();
        quantityItem += quantity;
    }

    public void removeItems(Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!item.getClass().isInstance(this.item))
            throw new ItemNotTypeException();
        if (quantityItem < quantity)
            throw new RuntimeException();//TODO:throw
        quantityItem -= quantity;
    }

    public Long getId() {
        return id;
    }

    public T getItem() {
        return item;
    }
}
