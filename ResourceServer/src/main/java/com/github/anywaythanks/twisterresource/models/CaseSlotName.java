package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.generators.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "case_slot_name")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Builder
@Data
public class CaseSlotName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "case_slot_name_seq")
    @GenericGenerator(
            name = "case_slot_name_seq",
            type = StringPrefixedSequenceIdGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "slot-"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1")})
    @NotBlank
    @Length(min = 1, max = 64)
    String name;
}
