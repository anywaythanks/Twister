package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.money.type.PathToIcon;

public class MoneyTypeCreateRequestDto implements PathToIcon {
    String pathToIcon;

    protected MoneyTypeCreateRequestDto() {
    }

    public MoneyTypeCreateRequestDto(String pathToIcon) {
        this.pathToIcon = pathToIcon;
    }

    @Override
    public String getPathToIcon() {
        return pathToIcon;
    }
}
