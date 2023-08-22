package com.github.anywaythanks.twisterresource.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

@Entity
@Table(name = "money_type")
public class MoneyType {
    @Id
    @GeneratedValue
    Integer id;
    @NotBlank
    @Length(min = 3, max = 64)
    @Column(nullable = false, unique = true)
    String name;

    @NotBlank
    @Length(min = 1, max = 64)
    @Column(nullable = false)
    String pathToIcon;

    protected MoneyType() {
    }

    public MoneyType(String name, String pathToIcon) {
        this.name = name;
        this.pathToIcon = pathToIcon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public void setPathToIcon(String visibleName) {
        this.pathToIcon = visibleName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MoneyType moneyType)) return false;
        return Objects.equals(getName(), moneyType.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
