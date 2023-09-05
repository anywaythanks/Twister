package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.ResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@ResponseDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class CaseLastTwistResponseDto implements LastTwist, Id {
    @NonNull Long id;
    @NonNull Instant lastTwist;
}