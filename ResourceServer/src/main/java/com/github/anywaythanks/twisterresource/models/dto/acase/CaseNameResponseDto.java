package com.github.anywaythanks.twisterresource.models.dto.acase;

public class CaseNameResponseDto implements Name {
    String name;

    protected CaseNameResponseDto() {
    }

    public CaseNameResponseDto(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
