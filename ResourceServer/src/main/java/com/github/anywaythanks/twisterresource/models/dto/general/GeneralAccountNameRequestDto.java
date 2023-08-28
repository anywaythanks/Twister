package com.github.anywaythanks.twisterresource.models.dto.general;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class GeneralAccountNameRequestDto implements Name {
    @NonNull
    String name;
}
