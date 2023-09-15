alter table if exists case_slots
    drop constraint FK_InventorySlots_Inventories

GO

alter table if exists case_slots
    drop constraint FK_InventorySlots_Items

GO

drop table inventory_slots


GO