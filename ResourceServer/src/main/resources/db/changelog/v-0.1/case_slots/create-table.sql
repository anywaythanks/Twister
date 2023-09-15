create table case_slots (
    percentage_wining numeric(38,10) not null check ((percentage_wining>=0) and (percentage_wining<=100)),
    quantity_item integer not null check (quantity_item>=0),
    case_id bigint not null,
    id bigint not null,
    item_id bigint not null,
    name varchar(255) not null unique,
    primary key (id)
)

GO

alter table if exists case_slots
    add constraint FK_CaseSlots_CaseSlotName
    foreign key (name)
    references case_slot_name

GO

create sequence case_slot_id_seq
minvalue 1
start with 1
increment by 50
cache 5

GO

alter sequence case_slot_id_seq owned by case_slots.id

GO

alter table if exists case_slots
    add constraint FK_CaseSlots_Cases
    foreign key (case_id)
    references cases

GO

alter table if exists case_slots
    add constraint FK_CaseSlots_Items
    foreign key (item_id)
    references items

GO