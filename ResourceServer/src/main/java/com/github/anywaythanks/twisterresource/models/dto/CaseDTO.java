package com.github.anywaythanks.twisterresource.models.dto;

import com.github.anywaythanks.twisterresource.validators.SumEq;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class CaseDTO {
    private interface Name {
        @NotBlank
        @Length(min = 1, max = 64)
        String getName();
    }

    public interface Price<T> {
        T getPrice();
    }

    private interface VisibleName {
        @NotBlank
        @Length(min = 1, max = 64)
        String getVisibleName();
    }

    private interface Description {
        @NotNull
        @Length(max = 1000)
        String getDescription();
    }

    private interface Cooldown {
        @NotNull
        Duration getCooldown();
    }

    private interface Id {
        @NotNull
        Long getId();
    }

    private interface Items<T> {
        @SumEq(eqSum = "1", converter = Converter.class)
        List<T> getItems();
    }

    private static class Converter implements Function<Object, BigDecimal> {
        @Override
        public BigDecimal apply(Object o) {
            if (o instanceof Slot.Percentage p) {
                return p.getPercentage();
            } else throw new RuntimeException();
        }
    }

    public static class Slot {
        private interface Name {
            @NotBlank
            @Length(min = 1, max = 64)
            String getName();
        }

        public interface Percentage {
            @Min(0)
            @Max(1)
            BigDecimal getPercentage();
        }

        public interface NameItem<T> {
            @NotNull
            T getItem();
        }

        public interface Item<T> {
            @NotNull
            T getItem();
        }


        public interface Quantity {
            @Min(1)
            Integer getQuantity();
        }

        public enum Request {
            ;

            public static class Create implements Percentage, NameItem<ItemDTO.Request.Name>, Quantity {
                BigDecimal percentage;
                ItemDTO.Request.Name item;
                Integer quantity;

                @Override
                public BigDecimal getPercentage() {
                    return percentage;
                }

                @Override
                public ItemDTO.Request.Name getItem() {
                    return item;
                }

                @Override
                public Integer getQuantity() {
                    return quantity;
                }
            }
        }

        public enum Response {
            ;
            public static class Partial implements Percentage, Item<ItemDTO.Response.Partial>, Quantity, Slot.Name {
                BigDecimal percentage;
                ItemDTO.Response.Partial item;
                Integer quantity;
                String name;

                public Partial() {
                }

                public Partial(BigDecimal percentage, ItemDTO.Response.Partial item, Integer quantity, String name) {
                    this.percentage = percentage;
                    this.item = item;
                    this.quantity = quantity;
                    this.name = name;
                }

                @Override
                public BigDecimal getPercentage() {
                    return percentage;
                }

                @Override
                public ItemDTO.Response.Partial getItem() {
                    return item;
                }

                @Override
                public Integer getQuantity() {
                    return quantity;
                }

                @Override
                public String getName() {
                    return name;
                }
            }
        }
    }

    public enum Request {
        ;

        public static class Name implements CaseDTO.Name {
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

        public static class Create implements Cooldown,
                Items<Slot.Request.Create>, Price<MoneyDTO.Request.Create>, VisibleName, Description {
            List<Slot.Request.Create> items;
            MoneyDTO.Request.Create price;
            Duration cooldown;
            String visibleName;
            String description;

            protected Create() {
            }

            public Create(List<Slot.Request.Create> items, MoneyDTO.Request.Create price,
                          Duration cooldown, String visibleName, String description) {
                this.items = items;
                this.price = price;
                this.cooldown = cooldown;
                this.visibleName = visibleName;
                this.description = description;
            }

            @Override
            public List<Slot.Request.Create> getItems() {
                return items;
            }

            @Override
            public MoneyDTO.Request.Create getPrice() {
                return price;
            }

            @Override
            public Duration getCooldown() {
                return cooldown;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            @Override
            public String getDescription() {
                return description;
            }
        }
    }

    public enum Response {
        ;

        public static class Name implements CaseDTO.Name {
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

        public static class Partial implements CaseDTO.Name, Cooldown, Items<Slot.Response.Partial>,
                Price<MoneyDTO.Response.Partial>, VisibleName, Description {
            List<Slot.Response.Partial> items;
            MoneyDTO.Response.Partial price;
            Duration cooldown;
            String name;
            String visibleName;
            String description;

            protected Partial() {

            }

            public Partial(List<Slot.Response.Partial> items, MoneyDTO.Response.Partial price,
                           Duration cooldown, String name, String visibleName, String description) {
                this.items = items;
                this.price = price;
                this.cooldown = cooldown;
                this.name = name;
                this.visibleName = visibleName;
                this.description = description;
            }

            @Override
            public List<Slot.Response.Partial> getItems() {
                return items;
            }

            @Override
            public MoneyDTO.Response.Partial getPrice() {
                return price;
            }

            @Override
            public Duration getCooldown() {
                return cooldown;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            public void setCooldown(Duration cooldown) {
                this.cooldown = cooldown;
            }

            @Override
            public String getDescription() {
                return description;
            }
        }

        public static class PartialWithoutCooldown implements CaseDTO.Name, Items<Slot.Response.Partial>,
                Price<MoneyDTO.Response.Partial>, VisibleName, Description {
            List<Slot.Response.Partial> items;
            MoneyDTO.Response.Partial price;
            String name;
            String visibleName;
            String description;

            protected PartialWithoutCooldown() {

            }

            public PartialWithoutCooldown(List<Slot.Response.Partial> items, MoneyDTO.Response.Partial price,
                                          String name, String visibleName, String description) {
                this.items = items;
                this.price = price;
                this.name = name;
                this.visibleName = visibleName;
                this.description = description;
            }

            @Override
            public List<Slot.Response.Partial> getItems() {
                return items;
            }

            @Override
            public MoneyDTO.Response.Partial getPrice() {
                return price;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            @Override
            public String getDescription() {
                return description;
            }
        }

        public static class CooldownId implements CaseDTO.Id, Cooldown {
            Long id;
            Duration cooldown;

            protected CooldownId() {

            }

            public CooldownId(Long id, Duration cooldown) {
                this.id = id;
                this.cooldown = cooldown;
            }

            @Override
            public Long getId() {
                return id;
            }

            @Override
            public Duration getCooldown() {
                return cooldown;
            }


            public void setCooldown(Duration cooldown) {
                this.cooldown = cooldown;
            }
        }

        public static class LightPartial implements CaseDTO.Name, Cooldown,
                Price<MoneyDTO.Response.Partial>, VisibleName, Description {
            MoneyDTO.Response.Partial price;
            Duration cooldown;
            String name;
            String visibleName;
            String description;

            protected LightPartial() {

            }

            public LightPartial(MoneyDTO.Response.Partial price, Duration cooldown,
                                String name, String visibleName, String description) {
                this.price = price;
                this.cooldown = cooldown;
                this.name = name;
                this.visibleName = visibleName;
                this.description = description;
            }

            @Override
            public MoneyDTO.Response.Partial getPrice() {
                return price;
            }

            @Override
            public Duration getCooldown() {
                return cooldown;
            }

            @Override
            public String getName() {
                return name;
            }

            public void setCooldown(Duration cooldown) {
                this.cooldown = cooldown;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            @Override
            public String getDescription() {
                return description;
            }
        }

        public static class LightPartialWithoutCooldown implements CaseDTO.Name,
                Price<MoneyDTO.Response.Partial>, VisibleName, Description {
            MoneyDTO.Response.Partial price;
            String name;
            String visibleName;
            String description;

            protected LightPartialWithoutCooldown() {

            }

            public LightPartialWithoutCooldown(MoneyDTO.Response.Partial price,
                                               String name, String visibleName, String description) {
                this.price = price;
                this.name = name;
                this.visibleName = visibleName;
                this.description = description;
            }

            @Override
            public MoneyDTO.Response.Partial getPrice() {
                return price;
            }

            @Override
            public String getName() {
                return name;
            }

            @Override
            public String getVisibleName() {
                return visibleName;
            }

            @Override
            public String getDescription() {
                return description;
            }
        }
    }
}
