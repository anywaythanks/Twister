package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.models.dto.account.CreatedOn;
import com.github.anywaythanks.twisterresource.models.dto.account.ModifiedBy;
import lombok.*;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class MoneyTypeFullDto implements Name, PathToIcon, Id, CreatedOn, ModifiedBy {
    @NonNull
    Integer id;
    @NonNull
    String name;
    @NonNull
    String pathToIcon;
    @NonNull
    Instant createdOn;
    @NonNull
    Instant modifiedBy;
}
