package com.example.twisterclient.models;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

import java.util.Objects;

public class GeneralAccountName {
    @NotBlank
    @Length(min = 3, max = 64)
    String name;

    protected GeneralAccountName() {
    }

    public GeneralAccountName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GeneralAccountName that)) return false;
        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
