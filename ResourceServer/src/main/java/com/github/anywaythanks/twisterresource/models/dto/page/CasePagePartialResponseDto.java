package com.github.anywaythanks.twisterresource.models.dto.page;

import com.github.anywaythanks.twisterresource.annotation.PartialResponseDto;
import com.github.anywaythanks.twisterresource.models.dto.acase.CaseLightPartialResponseDto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@PartialResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CasePagePartialResponseDto implements Values<CaseLightPartialResponseDto>, Total, Num {
    @NonNull List<CaseLightPartialResponseDto> values;
    @NonNull Integer totalPages;
    @NonNull Integer page;
}
