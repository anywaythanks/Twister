package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.money.type.Name;

public class MoneyTypeNameResponseDto implements Name {
    String name;

    protected MoneyTypeNameResponseDto() {
    }

    public MoneyTypeNameResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
