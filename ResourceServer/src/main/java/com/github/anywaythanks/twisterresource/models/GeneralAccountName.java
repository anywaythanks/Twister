package com.github.anywaythanks.twisterresource.models;

import com.github.anywaythanks.twisterresource.generators.StringPrefixedSequenceIdGenerator;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "general_account_name")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class GeneralAccountName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "general_account_name_seq")
    @GenericGenerator(
            name = "general_account_name_seq",
            type = StringPrefixedSequenceIdGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "id")})
    @NotBlank
    @Length(min = 3, max = 64)
    @NonNull
    String name;
}
