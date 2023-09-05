package com.github.anywaythanks.twisterresource.models.dto.money.type;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.CreatedOn;
import com.github.anywaythanks.twisterresource.models.dto.account.ModifiedBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
public class MoneyTypeFullDto implements Name, PathToIcon, Id, CreatedOn, ModifiedBy {
    @NonNull Integer id;
    @NonNull String name;
    @NonNull String pathToIcon;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
