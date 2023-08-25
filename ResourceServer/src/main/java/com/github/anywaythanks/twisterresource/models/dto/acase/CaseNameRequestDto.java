package com.github.anywaythanks.twisterresource.models.dto.acase;

public class CaseNameRequestDto implements Name {
    String name;

    protected CaseNameRequestDto() {
    }

    public CaseNameRequestDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
