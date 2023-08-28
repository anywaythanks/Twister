package com.github.anywaythanks.twisterresource.models.dto.acase;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseNameResponseDto implements Name {
    @NonNull
    String name;
}
