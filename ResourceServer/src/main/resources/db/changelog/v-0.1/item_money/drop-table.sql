alter table if exists item_money
    drop constraint FK_ItemMoney_MoneyType

GO

alter table if exists item_money
    drop constraint FK_ItemMoney_Items

GO

drop table item_money

GO