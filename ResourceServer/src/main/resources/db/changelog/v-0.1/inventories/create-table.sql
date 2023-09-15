create table inventories (
     created_on timestamp(6) with time zone not null,
     general_account_id bigint not null,
     id bigint not null,
     modified_by timestamp(6) with time zone not null,
     name varchar(255) not null unique,
     primary key (id)
)

GO

alter table if exists inventories
    add constraint FK_Inventories_GeneralAccounts
    foreign key (general_account_id)
    references general_accounts

GO

alter table if exists inventories
    add constraint FK_Inventories_InventoryName
    foreign key (name)
    references inventory_name

GO

create sequence inventory_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence inventory_id_seq owned by inventories.id

GO