package com.github.anywaythanks.twisterresource.models.dto.general;

public class GeneralAccountIdResponseDto implements Id, Uuid {
    Long id;
    String uuid;

    protected GeneralAccountIdResponseDto() {
    }

    public GeneralAccountIdResponseDto(Long id, String uuid) {
        this.id = id;
        this.uuid = uuid;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public String getUuid() {
        return uuid;
    }
}
