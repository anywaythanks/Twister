package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.money.type.Id;

public class MoneyTypeIdResponseDto implements Id {
    Integer id;

    protected MoneyTypeIdResponseDto() {
    }

    public MoneyTypeIdResponseDto(Integer id) {
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }
}
