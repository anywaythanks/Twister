package com.github.anywaythanks.twisterresource.models.dto.money.type;

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
