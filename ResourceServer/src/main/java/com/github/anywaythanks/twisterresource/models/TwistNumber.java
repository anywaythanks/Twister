package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.generators.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "twist_number")
@NoArgsConstructor(access = PROTECTED)
@AllArgsConstructor(access = PROTECTED)
@Getter
@Builder
public class TwistNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "twist_number_seq")
    @GenericGenerator(
            name = "twist_number_seq",
            type = StringPrefixedSequenceIdGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1")})
    @NotBlank
    @Length(min = 1, max = 64)
    String number;
}
