package com.github.anywaythanks.twisterresource.models.dto.item;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public interface VisibleName {
    @NotBlank
    @Length(min = 1, max = 64)
    String getVisibleName();
}
