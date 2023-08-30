package com.github.anywaythanks.twisterresource.models.dto.acase;

import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseLastTwistResponseDto implements LastTwist, Id {
    @NonNull
    Long id;
    @NonNull
    Instant lastTwist;
}