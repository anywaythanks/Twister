package com.github.anywaythanks.twisterresource.models.dto.acase;

import com.github.anywaythanks.twisterresource.annotation.FullDto;
import com.github.anywaythanks.twisterresource.models.dto.account.CreatedOn;
import com.github.anywaythanks.twisterresource.models.dto.account.ModifiedBy;
import com.github.anywaythanks.twisterresource.models.dto.money.MoneyFullDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Duration;
import java.time.Instant;

import static lombok.AccessLevel.PRIVATE;

@FullDto
@FieldDefaults(level = PRIVATE, makeFinal = true)
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class CaseFullDto implements Id, Cooldown, Price<MoneyFullDto>, VisibleName, Description, Name, CreatedOn, ModifiedBy {
    @NonNull Long id;
    @NonNull String name;
    @NonNull MoneyFullDto price;
    @With
    @NonNull Duration cooldown;
    @NonNull String visibleName;
    @NonNull String description;
    @NonNull Instant createdOn;
    @NonNull Instant modifiedBy;
}
