package com.example.twisterclient.models;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    String name;
    List<InventorySlot> slots = new ArrayList<>();

    protected Inventory() {
    }

    public Inventory(String name, List<InventorySlot> slots) {
        this.name = name;
        this.slots = slots;
    }

    public String getName() {
        return name;
    }

    public List<InventorySlot> getSlots() {
        return slots;
    }
}
