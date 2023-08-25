package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.money.type.Name;

public class MoneyTypeNameRequestDto implements Name {
    String name;

    protected MoneyTypeNameRequestDto() {
    }

    public MoneyTypeNameRequestDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
