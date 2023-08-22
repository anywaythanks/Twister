package com.github.anywaythanks.twisterresource.models.dto;

import java.time.Instant;

public class TwistDTO {
    private interface SelectCase<T> {
        T getSelectCase();
    }

    private interface Account<T> {
        T getAccount();
    }

    private interface TwistedOn {
        Instant getTwistedOn();
    }

    private interface Item<T> {
        T getItem();
    }

    private interface Quantity {
        Integer getQuantity();
    }

    public enum Request {
        ;

        public static class Create {

        }
    }

    public enum Response {
        ;

        public static class Partial implements SelectCase<CaseDTO.Response.Name>, Account<AccountDTO.Response.Number>,
                TwistedOn, Item<ItemDTO.Response.Partial>, Quantity {
            CaseDTO.Response.Name selectCase;
            AccountDTO.Response.Number account;
            Instant twistedOn;
            ItemDTO.Response.Partial item;
            Integer quantity;

            protected Partial() {
            }

            public Partial(CaseDTO.Response.Name selectCase,
                           AccountDTO.Response.Number account,
                           Instant twistedOn,
                           ItemDTO.Response.Partial item,
                           Integer quantity) {
                this.selectCase = selectCase;
                this.account = account;
                this.twistedOn = twistedOn;
                this.item = item;
                this.quantity = quantity;
            }

            @Override
            public CaseDTO.Response.Name getSelectCase() {
                return selectCase;
            }

            @Override
            public AccountDTO.Response.Number getAccount() {
                return account;
            }

            @Override
            public Instant getTwistedOn() {
                return twistedOn;
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
    }
}
