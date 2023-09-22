package com.example.twisterclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Duration;
import java.util.List;

public class Case {
    Duration cooldown;
    String name;
    @JsonProperty("visible_name")
    String visibleName;
    String description;
    Money price;
    List<CaseSlot> items;

    protected Case() {
    }

    public Case(Duration cooldown, String name, String visibleName, String description, Money price) {
        this.cooldown = cooldown;
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
        this.price = price;
    }

    public Case(Duration cooldown, String name, String visibleName, String description, Money price, List<CaseSlot> items) {
        this.cooldown = cooldown;
        this.name = name;
        this.visibleName = visibleName;
        this.description = description;
        this.price = price;
        this.items = items;
    }

    public List<CaseSlot> getItems() {
        return items;
    }

    public Duration getCooldown() {
        return cooldown;
    }

    public String getName() {
        return name;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public String getDescription() {
        return description;
    }

    public Money getPrice() {
        return price;
    }
}
