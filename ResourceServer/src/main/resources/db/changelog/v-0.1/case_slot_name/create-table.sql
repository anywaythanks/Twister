create table case_slot_name (
    name varchar(64) not null,
    primary key (name)
)

GO

create sequence case_slot_name_seq
    minvalue 1
    start with 1
    increment by 1
    cache 5

GO

alter sequence case_slot_name_seq owned by case_slot_name.name

GO