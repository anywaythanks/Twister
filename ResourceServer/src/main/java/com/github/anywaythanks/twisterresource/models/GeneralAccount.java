package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "general_accounts")
@NamedEntityGraph(name = "GeneralAccount.detail",
        attributeNodes = @NamedAttributeNode("name"))
public class GeneralAccount {
    @Id
    @GeneratedValue
    Long id;
    @NotBlank
    @Column(name = "user_uuid", nullable = false, unique = true)
    String userUuid;
    
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    GeneralAccountName name;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    String nickname;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "general_account_mapping",
            joinColumns = {@JoinColumn(name = "general_account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")})
    @MapKey(name = "number")
    Map<AccountNumber, Account> accounts = new HashMap<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BatchSize(size = 5)
    @JoinTable(name = "general_inventory_mapping",
            joinColumns = {@JoinColumn(name = "general_account_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "inventory_id", referencedColumnName = "id")})
    @MapKey(name = "name")
    Map<InventoryName, Inventory> inventories = new HashMap<>();

    protected GeneralAccount() {
    }

    public GeneralAccount(String userUuid, String nickname, GeneralAccountName name) {
        this.userUuid = userUuid;
        this.nickname = nickname;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public Map<AccountNumber, Account> getAccounts() {
        return accounts;
    }

    public GeneralAccountName getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public Map<InventoryName, Inventory> getInventories() {
        return inventories;
    }

    public void setName(GeneralAccountName name) {
        this.name = name;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
