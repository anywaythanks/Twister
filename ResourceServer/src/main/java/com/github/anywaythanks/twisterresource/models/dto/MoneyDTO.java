package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public class MoneyDTO {


    private interface Value {
        @NotNull
        @Positive
        BigDecimal getValue();
    }


    private interface NameType<T> {
        @NotNull
        T getType();
    }

    private interface GetterType<T> {
        @NotNull
        T getType();
    }

    public enum Request {
        ;

        public static class Create implements Value, NameType<Type.Request.Name> {
            Type.Request.Name type;
            BigDecimal value;

            protected Create() {
            }

            public Create(Type.Request.Name type, BigDecimal value) {
                this.type = type;
                this.value = value;
            }

            @Override
            public BigDecimal getValue() {
                return value;
            }

            @Override
            public Type.Request.Name getType() {
                return type;
            }
        }
    }

    public enum Response {
        ;

        public static class ContainId implements Value, GetterType<Type.Response.Id> {
            Type.Response.Id type;
            BigDecimal value;

            protected ContainId() {
            }

            public ContainId(Type.Response.Id type, BigDecimal value) {
                this.type = type;
                this.value = value;
            }

            @Override
            public BigDecimal getValue() {
                return value;
            }

            @Override
            public Type.Response.Id getType() {
                return type;
            }
        }

        public static class Partial implements Value, GetterType<Type.Response.Partial> {
            Type.Response.Partial type;
            BigDecimal value;

            protected Partial() {
            }

            public Partial(Type.Response.Partial type, BigDecimal value) {
                this.type = type;
                this.value = value;
            }

            @Override
            public BigDecimal getValue() {
                return value;
            }

            @Override
            public Type.Response.Partial getType() {
                return type;
            }
        }
    }

    public static class Type {
        private interface PathToIcon {
            @NotBlank
            @Length(min = 1, max = 64)
            String getPathToIcon();
        }

        private interface Name {

            @NotBlank
            @Length(min = 1, max = 64)
            String getName();
        }

        private interface Id {

            @NotNull
            Integer getId();
        }

        public enum Request {
            ;

            public static class Create implements PathToIcon {
                String pathToIcon;

                protected Create() {
                }

                public Create(String pathToIcon) {
                    this.pathToIcon = pathToIcon;
                }

                @Override
                public String getPathToIcon() {
                    return pathToIcon;
                }
            }

            public static class Name implements Type.Name {
                String name;

                protected Name() {
                }

                public Name(String name) {
                    this.name = name;
                }

                @Override
                public String getName() {
                    return name;
                }
            }
        }

        public enum Response {
            ;

            public static class Id implements Type.Id {
                Integer id;

                protected Id() {
                }

                public Id(Integer id) {
                    this.id = id;
                }

                @Override
                public Integer getId() {
                    return id;
                }
            }

            public static class Partial implements Type.Name, PathToIcon {
                String name;
                String pathToIcon;

                protected Partial() {
                }

                public Partial(String name, String pathToIcon) {
                    this.name = name;
                    this.pathToIcon = pathToIcon;
                }

                @Override
                public String getName() {
                    return name;
                }

                @Override
                public String getPathToIcon() {
                    return pathToIcon;
                }
            }

            public static class Name implements Type.Name {
                String name;

                protected Name() {
                }

                public Name(String name) {
                    this.name = name;
                }

                @Override
                public String getName() {
                    return name;
                }
            }
        }
    }
}
