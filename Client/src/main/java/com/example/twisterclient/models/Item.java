package com.example.twisterclient.models;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ItemMoney.class, name = TypesItem.Constants.MONEY_NAME),
        @JsonSubTypes.Type(value = ItemTrash.class, name = TypesItem.Constants.TRASH_NAME)
})
public abstract class Item {
    String name;
    String visibleName;

    protected Item() {
    }

    protected Item(String name, String visibleName) {
        this.name = name;
        this.visibleName = visibleName;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public String getName() {
        return name;
    }

}
