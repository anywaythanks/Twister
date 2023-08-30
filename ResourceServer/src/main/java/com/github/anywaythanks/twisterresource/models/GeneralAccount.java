package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.Query;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "general_accounts")
@NamedEntityGraph(name = "GeneralAccount.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RequiredArgsConstructor
@Getter
public class GeneralAccount {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @Column(name = "user_uuid", nullable = false, unique = true)
    @NonNull
    String userUuid;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    @NonNull
    @Setter
    GeneralAccountName name;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @NonNull
    @Setter
    String nickname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinColumn(name = "general_account_id", nullable = false)
    @NonNull
    Set<Account> accounts = new HashSet<>();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinColumn(name = "general_account_id", nullable = false)
    @NonNull
    Set<Inventory> inventories = new HashSet<>();

    @NotNull
    @Column(name = "modified_by", nullable = false)
    @NonNull
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    @NonNull
    Instant createdOn;
}