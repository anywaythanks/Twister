create table general_account_name (
  name varchar(64) not null,
  primary key (name)
)

GO

create sequence general_account_name_seq
    minvalue 1
    start with 1
    increment by 1
    cache 5

GO

alter sequence general_account_name_seq owned by general_account_name.name

GO