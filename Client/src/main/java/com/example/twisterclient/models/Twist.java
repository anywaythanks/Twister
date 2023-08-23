package com.example.twisterclient.models;

public class Twist {
    Item item;
    Integer quantity;
    String caseName;

    protected Twist() {
    }

    public Twist(Item item, Integer quantity, String caseName) {
        this.item = item;
        this.quantity = quantity;
        this.caseName = caseName;
    }

    public Item getItem() {
        return item;
    }

    public Integer getQuantity() {
        return quantity;
    }
}