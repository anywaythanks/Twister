package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Entity
@Table(name = "general_accounts")
@NamedEntityGraph(name = "GeneralAccount.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
public class GeneralAccount {
    @Id
    @GeneratedValue
    @Setter
    Long id;
    @NotBlank
    @Column(name = "user_uuid", nullable = false, unique = true)
    @NonNull
    @Setter
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
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @BatchSize(size = 5)
//    @JoinColumn(name = "general_account_id", nullable = false)
//    @NonNull
//    Set<Account> accounts = new HashSet<>();
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @BatchSize(size = 5)
//    @JoinColumn(name = "general_account_id", nullable = false)
//    @NonNull
//    Set<Inventory> inventories = new HashSet<>();

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