create table money_type (
    id integer not null,
    created_on timestamp(6) with time zone not null,
    modified_by timestamp(6) with time zone not null,
    name varchar(64) not null unique,
    path_to_icon varchar(64) not null,
    primary key (id)
)

GO

create sequence money_type_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence money_type_id_seq owned by money_type.id

GO
