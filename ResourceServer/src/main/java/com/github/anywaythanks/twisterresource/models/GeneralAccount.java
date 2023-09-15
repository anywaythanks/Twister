package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "general_accounts")
@NamedEntityGraph(name = "GeneralAccount.detail",
        attributeNodes = @NamedAttributeNode("name"))
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
public class GeneralAccount {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "general_account_seq")
    @SequenceGenerator(name = "general_account_seq", sequenceName = "general_account_id_seq")
    Long id;
    @NotBlank
    @Column(name = "user_uuid", nullable = false, unique = true)
    @Setter
    String userUuid;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "name", nullable = false, unique = true)
    @Setter
    GeneralAccountName name;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    @Setter
    String nickname;
    @NotNull
    @Column(name = "modified_by", nullable = false)
    @Setter
    Instant modifiedBy;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}