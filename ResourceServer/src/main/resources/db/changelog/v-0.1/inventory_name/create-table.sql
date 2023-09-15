create table inventory_name (
  name varchar(64) not null,
  primary key (name)
)

GO

create sequence inventory_name_seq
    minvalue 1
    start with 1
    increment by 1
    cache 5

GO

alter sequence inventory_name_seq owned by inventory_name.name

GO
