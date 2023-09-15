create table items (
      created_on timestamp(6) with time zone not null,
      id bigint not null,
      modified_by timestamp(6) with time zone not null,
      name varchar(64) not null unique,
      visible_name varchar(64) not null,
      primary key (id)
)

GO

create sequence item_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence item_id_seq owned by items.id

GO
