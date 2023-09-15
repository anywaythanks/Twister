alter table if exists inventories
    drop constraint FK_Inventories_GeneralAccounts

GO

alter table if exists inventories
    drop constraint FK_Inventories_InventoryName

GO

drop table inventories


GO