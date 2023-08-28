package com.github.anywaythanks.twisterresource.models.dto.acase;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseNameRequestDto implements Name {
    @NonNull
    String name;
}
