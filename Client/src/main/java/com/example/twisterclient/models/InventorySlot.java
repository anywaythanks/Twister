package com.example.twisterclient.models;

public class InventorySlot {
    Integer quantity;
    Item item;

    protected InventorySlot() {
    }

    public InventorySlot(Integer quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Item getItem() {
        return item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
