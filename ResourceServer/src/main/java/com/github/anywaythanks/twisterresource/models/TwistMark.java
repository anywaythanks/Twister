package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "twist_marks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class TwistMark {
    @Id
    @GeneratedValue
    Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JoinColumn(name = "twist_case_id", nullable = false, insertable = false, updatable = false)
    @NonNull
    Case twistCase;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "general_account_id", nullable = false, insertable = false, updatable = false)
    @NonNull
    GeneralAccount generalAccount;
    @NotNull
    @Column(nullable = false)
    @NonNull
    @Setter
    Boolean consider;
    @NotNull
    @Column(name = "updated_on", nullable = false)
    @NonNull
    @Setter
    Instant updatedOn;
    @NotNull
    @Column(name = "created_on", nullable = false)
    @NonNull
    Instant createdOn;
}
