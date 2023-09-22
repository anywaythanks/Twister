package com.example.twisterclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class CaseSlot {
    Item item;
    Long quantity;
    @JsonProperty("win_rate")
    BigDecimal percentage;
    String name;

    protected CaseSlot() {
    }

    public CaseSlot(Item item, Long quantity, BigDecimal percentage, String name) {
        this.item = item;
        this.quantity = quantity;
        this.percentage = percentage;
        this.name = name;
    }

    public Item getItem() {
        return item;
    }

    public Long getQuantity() {
        return quantity;
    }

    public BigDecimal getPercentage() {
        return percentage;
    }

    public String getName() {
        return name;
    }
}
