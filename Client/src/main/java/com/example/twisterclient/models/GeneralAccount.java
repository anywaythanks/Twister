package com.example.twisterclient.models;

public class GeneralAccount {
    String name;
    String nickname;

    public GeneralAccount() {
    }

    public GeneralAccount(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }
}
