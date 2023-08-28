package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.exceptions.ItemNotTypeException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public abstract class Slot<T extends Item> {
    @Id
    @GeneratedValue(generator = "SLOT_ID_GENERATOR")
    Long id;
    @NotNull
    @ManyToOne(targetEntity = Item.class)
    @JoinColumn(name = "item_id", nullable = false)
    @NonNull
    T item;
    @NotNull
    @Min(0)
    @Column(name = "quantity_item", nullable = false)
    @NonNull
    Integer quantityItem;

    public void addItems(@NonNull Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!item.getClass().isInstance(this.item))
            throw new ItemNotTypeException();
        quantityItem += quantity;
    }

    public void removeItems(@NonNull Item item, int quantity) {
        if (quantity < 0)
            throw new IllegalArgumentException();
        if (!item.getClass().isInstance(this.item))
            throw new ItemNotTypeException();
        if (quantityItem < quantity)
            throw new RuntimeException();//TODO:throw
        quantityItem -= quantity;
    }
}
