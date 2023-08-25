package com.example.twisterclient.models.dto;

public class SellItemDtoRequest {
    private Integer quantity;

    public SellItemDtoRequest() {
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
