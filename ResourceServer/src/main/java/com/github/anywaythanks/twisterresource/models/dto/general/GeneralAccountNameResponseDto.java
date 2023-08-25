package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountNameResponseDto implements Name {
    String name;

    protected GeneralAccountNameResponseDto() {
    }

    public GeneralAccountNameResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
