package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;

import static jakarta.persistence.GenerationType.SEQUENCE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "twist_marks")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
public class TwistMark {
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "twist_marks_seq")
    @SequenceGenerator(name = "twist_marks_seq", sequenceName = "twist_marks_id_seq")
    Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @NotNull
    @JoinColumn(name = "twist_case_id", nullable = false)
    Case twistCase;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "general_account_id", nullable = false)
    GeneralAccount generalAccount;
    @NotNull
    @Column(nullable = false)
    @Setter
    Boolean consider;
    @NotNull
    @Column(name = "updated_on", nullable = false)
    @Setter
    Instant updatedOn;
    @NotNull
    @Column(name = "created_on", nullable = false)
    Instant createdOn;
}
