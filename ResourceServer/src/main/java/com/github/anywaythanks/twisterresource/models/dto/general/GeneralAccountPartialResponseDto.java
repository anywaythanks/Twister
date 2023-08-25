package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountPartialResponseDto implements Name, Nickname {
    String nickname;
    String name;

    protected GeneralAccountPartialResponseDto() {
    }

    public GeneralAccountPartialResponseDto(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }

    @Override
    public String getName() {
        return name;
    }
}
