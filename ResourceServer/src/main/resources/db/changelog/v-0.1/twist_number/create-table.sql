create table twist_number (
      number varchar(64) not null,
      primary key (number)
)

GO

create sequence twist_number_seq
    minvalue 1
    start with 1
    increment by 1
    cache 5

GO

alter sequence twist_number_seq owned by twist_number.number

GO
