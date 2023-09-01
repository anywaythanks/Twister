package com.github.anywaythanks.twisterresource.models.dto.acase;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class CaseIdDto implements Id {
    @NonNull
    Long id;
}
