package com.example.twisterclient.models;

public class ItemMoney extends Item implements SellingItem {
    private Money cost;

    public ItemMoney() {
    }

    public ItemMoney(String name, String visibleName, Money cost) {
        super(name, visibleName);
        this.cost = cost;
    }
    @Override
    public Money getCost() {
        return cost;
    }
}
