package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.money.type.Name;
import com.github.anywaythanks.twisterresource.models.dto.money.type.PathToIcon;

public class MoneyTypePartialResponseDto implements Name, PathToIcon {
    String name;
    String pathToIcon;

    protected MoneyTypePartialResponseDto() {
    }

    public MoneyTypePartialResponseDto(String name, String pathToIcon) {
        this.name = name;
        this.pathToIcon = pathToIcon;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPathToIcon() {
        return pathToIcon;
    }
}
