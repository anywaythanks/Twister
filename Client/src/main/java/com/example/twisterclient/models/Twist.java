package com.example.twisterclient.models;

public class Twist {
    String wonSlotName;

    protected Twist() {
    }

    public Twist(String wonSlotName) {
        this.wonSlotName = wonSlotName;
    }

    public String getWonSlotName() {
        return wonSlotName;
    }
}