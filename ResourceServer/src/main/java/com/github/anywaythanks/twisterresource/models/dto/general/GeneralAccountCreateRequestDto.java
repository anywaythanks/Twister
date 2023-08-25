package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountCreateRequestDto implements Nickname {
    String nickname;

    @Override
    public String getNickname() {
        return nickname;
    }
}
