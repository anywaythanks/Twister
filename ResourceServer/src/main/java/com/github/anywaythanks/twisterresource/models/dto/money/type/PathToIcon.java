package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

interface PathToIcon {
    @NotBlank
    @Length(min = 1, max = 64)
    @JsonProperty("path_to_icon")
    String getPathToIcon();
}
