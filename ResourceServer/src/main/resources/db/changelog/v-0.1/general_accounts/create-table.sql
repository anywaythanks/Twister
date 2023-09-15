create table general_accounts (
      created_on timestamp(6) with time zone not null,
      id bigint not null,
      modified_by timestamp(6) with time zone not null,
      nickname varchar(64) not null unique,
      name varchar(64) not null unique,
      user_uuid varchar(64) not null unique,
      primary key (id)
)

GO

alter table if exists general_accounts
    add constraint FK_GeneralAccounts_GeneralAccountName
    foreign key (name)
    references general_account_name

GO

create sequence general_account_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence general_account_id_seq owned by general_accounts.id

GO
