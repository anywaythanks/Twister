package com.example.twisterclient.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    public static class Type {
        String name;
        @JsonProperty("path_to_icon")
        String pathToIcon;

        protected Type() {
        }

        public Type(String name, String pathToIcon) {
            this.name = name;
            this.pathToIcon = pathToIcon;
        }

        public String getName() {
            return name;
        }

        public String getPathToIcon() {
            return pathToIcon;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Type type)) return false;
            return Objects.equals(getName(), type.getName());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getName());
        }
    }

    Type type;
    BigDecimal value;

    protected Money() {
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
