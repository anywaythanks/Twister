package com.github.anywaythanks.twisterresource.models.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class ItemDTO {
    private interface Id {
        @NotNull
        Long getId();
    }

    private interface Name {
        @NotBlank
        @Length(min = 1, max = 64)
        String getName();
    }

    private interface VisibleName {
        @NotBlank
        @Length(min = 1, max = 64)
        String getVisibleName();
    }

    private interface Cost<T> {
        @NotNull
        T getCost();
    }

    private interface Type {
        @NotNull
        Types getType();
    }

    public enum Types {
        MONEY(Constants.MONEY_NAME), TRASH(Constants.TRASH_NAME);

        Types(String name) {
            if (!name.equals(this.name()))
                throw new IllegalArgumentException();
        }

        public static class Constants {
            public static final String MONEY_NAME = "MONEY";
            public static final String TRASH_NAME = "TRASH";
        }

        @Override
        public String toString() {
            return super.toString();
        }
    }

    public enum Request {
        ;

        public static class Name implements ItemDTO.Name {
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

        @JsonTypeInfo(
                use = JsonTypeInfo.Id.NAME,
                property = "type",
                visible = true)
        @JsonSubTypes({
                @JsonSubTypes.Type(value = CreateMoney.class, name = Types.Constants.MONEY_NAME),
                @JsonSubTypes.Type(value = CreateTrash.class, name = Types.Constants.TRASH_NAME)
        })
        public static abstract class CreateItem implements VisibleName, Type {
            String visibleName;
            Types type;

            protected CreateItem() {
            }

            protected CreateItem(String visibleName) {
                this.visibleName = visibleName;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            @Override
            public Types getType() {
                return type;
            }
        }

        public static class CreateTrash extends CreateItem {
            protected CreateTrash() {
            }

            public CreateTrash(String visibleName) {
                super(visibleName);
            }

        }

        public static class CreateMoney extends CreateItem implements Cost<MoneyDTO.Request.Create> {
            MoneyDTO.Request.Create cost;

            protected CreateMoney() {
            }

            public CreateMoney(MoneyDTO.Request.Create cost, String visibleName) {
                this.cost = cost;
                this.visibleName = visibleName;
            }

            @Override
            public MoneyDTO.Request.Create getCost() {
                return cost;
            }
        }
    }

    public enum Response {
        ;

        public static class Name implements ItemDTO.Name {
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

        public static class PartialMoney extends Partial implements Cost<MoneyDTO.Response.Partial> {
            MoneyDTO.Response.Partial cost;

            protected PartialMoney() {
            }

            public PartialMoney(ItemDTO.Types type, MoneyDTO.Response.Partial cost, String name, String visibleName) {
                super(type, name, visibleName);
                this.cost = cost;
            }

            @Override
            public MoneyDTO.Response.Partial getCost() {
                return cost;
            }
        }

        public static class PartialTrash extends Partial {

            protected PartialTrash() {
            }

            public PartialTrash(ItemDTO.Types type, String name, String visibleName) {
                super(type, name, visibleName);
            }
        }

        public static abstract class Partial implements ItemDTO.Name, ItemDTO.Type, VisibleName {
            ItemDTO.Types type;
            String name;
            String visibleName;

            protected Partial() {
            }

            protected Partial(ItemDTO.Types type, String name, String visibleName) {
                this.type = type;
                this.name = name;
                this.visibleName = visibleName;
            }

            @Override
            public ItemDTO.Types getType() {
                return type;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }
        }

        public static class Id implements ItemDTO.Id {
            Long id;

            protected Id() {
            }

            public Id(Long id) {
                this.id = id;
            }

            @Override
            public Long getId() {
                return id;
            }
        }
    }
}
