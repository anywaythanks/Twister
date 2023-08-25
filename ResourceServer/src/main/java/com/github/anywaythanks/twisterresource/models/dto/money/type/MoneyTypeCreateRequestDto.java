package com.github.anywaythanks.twisterresource.models.dto.money.type;

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
