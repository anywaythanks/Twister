package com.example.twisterclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigDecimal;
import java.util.Objects;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ItemMoney.class, name = TypesItem.Constants.MONEY_NAME),
        @JsonSubTypes.Type(value = ItemTrash.class, name = TypesItem.Constants.TRASH_NAME)
})
public abstract class Item {
    String name;
    @JsonProperty("visible_name")
    String visibleName;
    TypesItem type;

    protected Item() {
    }

    protected Item(String name, String visibleName, TypesItem type) {
        this.name = name;
        this.visibleName = visibleName;
    }

    public String getVisibleName() {
        return visibleName;
    }

    public String getName() {
        return name;
    }

    public TypesItem getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item item)) return false;
        return Objects.equals(getName(), item.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
