package com.github.anywaythanks.twisterresource.models.dto.account;

import com.github.anywaythanks.twisterresource.models.dto.money.type.MoneyTypeNameRequestDto;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class AccountCreateRequestDto implements NameType<MoneyTypeNameRequestDto> {
    @NonNull
    MoneyTypeNameRequestDto type;
}
