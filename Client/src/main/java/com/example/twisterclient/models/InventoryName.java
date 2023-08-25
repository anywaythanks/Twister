package com.example.twisterclient.models;

public class InventoryName {
    String name;

    protected InventoryName() {
    }

    public InventoryName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
