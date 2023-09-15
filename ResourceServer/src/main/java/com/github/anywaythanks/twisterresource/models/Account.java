package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "accounts")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "account_seq")
    @SequenceGenerator(name = "account_seq", sequenceName = "account_id_seq")
    Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "number", nullable = false, unique = true)
    AccountNumber number;

    @NotNull
    @Column(nullable = false)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "value", column = @Column(name = "amount", nullable = false))
    })
    @Setter
    Money amount;

    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "general_account_id")
    GeneralAccount generalAccount;
}