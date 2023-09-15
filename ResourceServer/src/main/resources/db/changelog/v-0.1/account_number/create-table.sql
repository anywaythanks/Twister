create table account_number (
    number varchar(64) not null,
    primary key (number)
)

GO

create sequence account_number_seq
    minvalue 1
    start with 1
    increment by 1
    cache 5

GO

alter sequence account_number_seq owned by account_number.number

GO

