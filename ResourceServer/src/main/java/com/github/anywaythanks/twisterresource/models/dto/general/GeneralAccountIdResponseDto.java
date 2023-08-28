package com.github.anywaythanks.twisterresource.models.dto.general;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class GeneralAccountIdResponseDto implements Id, Uuid {
    @NonNull
    Long id;
    @NonNull
    String uuid;
}
