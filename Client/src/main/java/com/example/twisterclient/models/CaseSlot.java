package com.example.twisterclient.models;

import java.math.BigDecimal;

public class CaseSlot {
    Item item;
    Long quantity;
    BigDecimal percentage;

    protected CaseSlot() {
    }

    public CaseSlot(Item item, Long quantity, BigDecimal percentage) {
        this.item = item;
        this.quantity = quantity;
        this.percentage = percentage;
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
}
