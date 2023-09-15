create table accounts (
      amount numeric(38,2) not null,
      money_type_id integer not null,
      created_on timestamp(6) with time zone not null,
      general_account_id bigint not null,
      id bigint not null,
      modified_by timestamp(6) with time zone not null,
      number varchar(64) not null unique,
      primary key (id)
)

GO

create sequence account_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence account_id_seq owned by accounts.id

GO

alter table if exists accounts
    add constraint FK_Accounts_MoneyType
    foreign key (money_type_id)
    references money_type

GO

alter table if exists accounts
    add constraint FK_Accounts_GeneralAccounts
    foreign key (general_account_id)
    references general_accounts

GO

alter table if exists accounts
    add constraint FK_Accounts_AccountNumber
    foreign key (number)
    references account_number

GO