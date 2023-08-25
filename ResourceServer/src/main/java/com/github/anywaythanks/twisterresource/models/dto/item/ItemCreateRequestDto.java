package com.github.anywaythanks.twisterresource.models.dto.item;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "type",
        visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = ItemMoneyCreateRequestDto.class, name = ItemTypes.Constants.MONEY_NAME),
        @JsonSubTypes.Type(value = ItemTrashCreateRequestDto.class, name = ItemTypes.Constants.TRASH_NAME)
})
public abstract class ItemCreateRequestDto implements VisibleName, Type {
    protected String visibleName;
    protected ItemTypes type;

    public ItemCreateRequestDto() {
    }

    public ItemCreateRequestDto(String visibleName) {
        this.visibleName = visibleName;
    }

    @Override
    public String getVisibleName() {
        return visibleName;
    }

    @Override
    public ItemTypes getType() {
        return type;
    }
}
