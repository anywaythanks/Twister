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
@Table(name = "inventories_name")
@NoArgsConstructor
@RequiredArgsConstructor
@Data
public class InventoryName {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_name_seq")
    @GenericGenerator(
            name = "inventory_name_seq",
            type = StringPrefixedSequenceIdGenerator.class,
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1")})
    @NotBlank
    @Length(min = 1, max = 64)
    @NonNull
    String name;
}
