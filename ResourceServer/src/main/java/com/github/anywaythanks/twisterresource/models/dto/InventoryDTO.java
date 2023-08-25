package com.github.anywaythanks.twisterresource.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class InventoryDTO {
    private interface Name {
        @NotBlank
        String getName();
    }

    private interface Id {
        @NotBlank
        Long getId();
    }

    private interface Slots<T> {
        List<T> getSlots();
    }

    public enum Request {
        ;

        public static class Name implements InventoryDTO.Name {
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

        public static class Name implements InventoryDTO.Name {
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

        public static class Id implements InventoryDTO.Id {
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

        public static class Partial implements Slots<SlotDTO.Response.Partial>, InventoryDTO.Name {
            List<SlotDTO.Response.Partial> slots;
            String name;

            protected Partial() {
            }

            public Partial(List<SlotDTO.Response.Partial> slots, String name) {
                this.slots = slots;
                this.name = name;
            }

            @Override
            public List<SlotDTO.Response.Partial> getSlots() {
                return slots;
            }

            @Override
            public String getName() {
                return name;
            }
        }
    }
}
