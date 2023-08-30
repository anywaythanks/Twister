package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.validator.constraints.Length;

import java.util.HashMap;
import java.util.Map;

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
    @JoinTable(name = "general_account_mapping",
            joinColumns = {@JoinColumn(name = "general_account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")})
    @MapKey(name = "number")
    @NonNull
    Map<AccountNumber, Account> accounts = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "general_inventory_mapping",
            joinColumns = {@JoinColumn(name = "general_account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "inventory_id", referencedColumnName = "id")})
    @MapKey(name = "name")
    @NonNull
    Map<InventoryName, Inventory> inventories = new HashMap<>();
}
