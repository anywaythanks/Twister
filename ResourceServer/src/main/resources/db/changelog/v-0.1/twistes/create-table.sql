create table twistes (
     quantity_item integer not null,
     account_id bigint not null,
     case_id bigint not null,
     created_on timestamp(6) with time zone not null,
     general_account_id bigint not null,
     id bigint not null,
     item_id bigint not null,
     number varchar(255) not null unique,
     primary key (id)
)


GO

alter table if exists twistes
    add constraint FK_Twistes_Accounts
    foreign key (account_id)
    references accounts

GO

alter table if exists twistes
    add constraint FK_Twistes_GeneralAccounts
    foreign key (general_account_id)
    references general_accounts

GO

alter table if exists twistes
    add constraint FK_Twistes_TwistNumber
    foreign key (number)
    references twist_number

GO

alter table if exists twistes
    add constraint FK_Twistes_Cases
    foreign key (case_id)
    references cases

GO

alter table if exists twistes
    add constraint FK_Twistes_Items
    foreign key (item_id)
    references items

GO

create sequence twist_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence twist_id_seq owned by twistes.id

GO

