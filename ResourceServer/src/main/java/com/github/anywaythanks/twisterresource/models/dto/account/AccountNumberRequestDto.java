package com.github.anywaythanks.twisterresource.models.dto.account;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class AccountNumberRequestDto implements Number {
    @NonNull
    String number;
}
