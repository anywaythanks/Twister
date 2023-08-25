package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountNicknameRequestDto implements Nickname {
    String nickname;

    public GeneralAccountNicknameRequestDto(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String getNickname() {
        return nickname;
    }
}
