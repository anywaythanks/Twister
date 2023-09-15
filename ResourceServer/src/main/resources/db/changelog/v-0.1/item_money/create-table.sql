create table item_money (
    cost numeric(38,2),
    money_type_id integer not null,
    item_money_id bigint not null,
    primary key (item_money_id)
)

GO

alter table if exists item_money
    add constraint FK_ItemMoney_MoneyType
    foreign key (money_type_id)
    references money_type

GO

alter table if exists item_money
    add constraint FK_ItemMoney_Items
    foreign key (item_money_id)
    references items

GO