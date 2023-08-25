package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountNameRequestDto implements Name {
    String name;

    protected GeneralAccountNameRequestDto() {
    }

    public GeneralAccountNameRequestDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
