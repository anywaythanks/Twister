package com.github.anywaythanks.twisterresource.models.dto.general;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class GeneralAccountPartialResponseDto implements Name, Nickname {
    @NonNull
    String nickname;
    @NonNull
    String name;
}
