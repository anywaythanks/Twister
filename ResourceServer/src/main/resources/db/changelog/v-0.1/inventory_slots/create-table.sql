create table inventory_slots (
     quantity_item integer not null check (quantity_item>=0),
     id bigint not null,
     inventory_id bigint not null,
     item_id bigint not null,
     primary key (id)
)

GO

create sequence inventory_slot_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence inventory_slot_id_seq owned by inventory_slots.id

GO

alter table if exists inventory_slots
    add constraint FK_InventorySlots_Inventories
    foreign key (inventory_id)
    references inventories

GO

alter table if exists inventory_slots
    add constraint FK_InventorySlots_Items
    foreign key (item_id)
    references items
GO