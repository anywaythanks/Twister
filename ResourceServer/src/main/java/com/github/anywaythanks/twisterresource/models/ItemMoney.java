package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@PrimaryKeyJoinColumn(name = "item_money_id")
@Table(name = "item_money")
public class ItemMoney extends Item implements SellingItem {
    public ItemMoney() {
    }

    public ItemMoney(String name, String visibleName, Money cost) {
        setName(name);
        setVisibleName(visibleName);
        this.cost = cost;
    }

    @NotNull
    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "cost")),
    })
    Money cost;


    @Override
    public Money getCost() {
        return cost;
    }

    public void setCost(Money cost) {
        this.cost = cost;
    }
}
