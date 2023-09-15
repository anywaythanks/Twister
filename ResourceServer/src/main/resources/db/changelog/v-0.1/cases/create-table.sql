create table cases (
   cooldown numeric(21,0) not null,
   money_type_id integer not null,
   price numeric(38,2) not null,
   created_on timestamp(6) with time zone not null,
   id bigint not null,
   modified_by timestamp(6) with time zone not null,
   name varchar(64) not null unique,
   visible_name varchar(64) not null,
   description varchar(1000) not null,
   primary key (id)
)

GO

alter table if exists cases
    add constraint FK_Cases_MoneyType
    foreign key (money_type_id)
    references money_type

GO

create sequence case_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence case_id_seq owned by cases.id

GO