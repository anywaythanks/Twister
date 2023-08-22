package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public class AccountDTO {
    private interface Number {
        @NotBlank
        @Length(min = 1, max = 64)
        String getNumber();
    }

    private interface Amount<T> {
        @NotNull
        T getAmount();
    }

    private interface NameType<T> {
        @NotNull
        T getType();
    }

    private interface Id {
        @NotNull
        Long getId();
    }

    private interface Slots<T> {
        @NotNull
        List<T> getSlots();
    }

    public enum Request {
        ;

        public static class Create implements NameType<MoneyDTO.Type.Request.Name> {
            MoneyDTO.Type.Request.Name type;

            @Override
            public MoneyDTO.Type.Request.Name getType() {
                return type;
            }
        }

        public static class Number implements AccountDTO.Number {
            String number;

            protected Number() {
            }

            public Number(String number) {
                this.number = number;
            }

            public Number(Long number) {
                this.number = number.toString();
            }

            @Override
            public String getNumber() {
                return number;
            }
        }
    }

    public enum Response {
        ;

        public static class Partial implements Amount<MoneyDTO.Response.Partial>, AccountDTO.Number {
            MoneyDTO.Response.Partial amount;
            String number;

            protected Partial() {

            }

            public Partial(String number, MoneyDTO.Response.Partial amount) {
                this.number = number;
                this.amount = amount;
            }

            @Override
            public String getNumber() {
                return number;
            }

            @Override
            public MoneyDTO.Response.Partial getAmount() {
                return amount;
            }
        }

        public static class Credit extends Id {
            protected Credit() {

            }

            public Credit(Long id) {
                super(id);
            }
        }

        public static class Debit extends Id {
            protected Debit() {

            }

            public Debit(Long id) {
                super(id);
            }
        }

        public static class Id implements AccountDTO.Id {
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

        public static class Number implements AccountDTO.Number {
            String number;

            public Number(String number) {
                this.number = number;
            }

            @Override
            public String getNumber() {
                return number;
            }
        }
    }
}
