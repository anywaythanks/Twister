package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SlotDTO {
    private interface Quantity {
        @Min(0)
        Integer getQuantity();
    }

    private interface ItemTypes<T> {
        @NotNull
        T getItem();
    }

    private interface Id {
        @NotNull
        Long getId();
    }

    public enum Request {
        ;

        public static class Quantity implements SlotDTO.Quantity {
            Integer quantity;

            protected Quantity() {
            }

            public Quantity(Integer quantity) {
                this.quantity = quantity;
            }

            @Override
            public Integer getQuantity() {
                return quantity;
            }
        }

        public static class Transfer implements SlotDTO.Quantity, ItemTypes<ItemDTO.Response.Id> {
            Integer quantity;
            ItemDTO.Response.Id item;

            public Transfer(Integer quantity, ItemDTO.Response.Id item) {
                this.quantity = quantity;
                this.item = item;
            }

            @Override
            public Integer getQuantity() {
                return quantity;
            }

            @Override
            public ItemDTO.Response.Id getItem() {
                return item;
            }
        }

        public static class Sell implements SlotDTO.Quantity, ItemTypes<ItemDTO.Request.Name> {
            Integer quantity;
            ItemDTO.Request.Name item;

            public Sell(Integer quantity, ItemDTO.Request.Name item) {
                this.quantity = quantity;
                this.item = item;
            }

            @Override
            public Integer getQuantity() {
                return quantity;
            }

            @Override
            public ItemDTO.Request.Name getItem() {
                return item;
            }
        }
    }

    public enum Response {
        ;

        public static class Partial implements SlotDTO.Quantity, ItemTypes<ItemDTO.Response.Partial> {
            ItemDTO.Response.Partial item;
            Integer quantity;

            protected Partial() {
            }

            public Partial(Integer quantity, ItemDTO.Response.Partial item) {
                this.quantity = quantity;
                this.item = item;
            }

            @Override
            public ItemDTO.Response.Partial getItem() {
                return item;
            }

            @Override
            public Integer getQuantity() {
                return quantity;
            }
        }

        public static class Ids implements SlotDTO.Quantity, ItemTypes<ItemDTO.Response.Id>, Id {
            ItemDTO.Response.Id item;
            Integer quantity;
            Long id;

            protected Ids() {
            }

            public Ids(Integer quantity, ItemDTO.Response.Id item, Long id) {
                this.quantity = quantity;
                this.item = item;
                this.id = id;
            }

            @Override
            public ItemDTO.Response.Id getItem() {
                return item;
            }

            @Override
            public Integer getQuantity() {
                return quantity;
            }

            @Override
            public Long getId() {
                return id;
            }
        }
    }
}
