create table twist_marks (
     consider boolean not null,
     created_on timestamp(6) with time zone not null,
     general_account_id bigint not null,
     id bigint not null,
     twist_case_id bigint not null,
     updated_on timestamp(6) with time zone not null,
     primary key (id)
)

GO

alter table if exists twist_marks
    add constraint FK_TwistMarks_GeneralAccounts
    foreign key (general_account_id)
    references general_accounts

GO

alter table if exists twist_marks
    add constraint FK_TwistMarks_Cases
    foreign key (twist_case_id)
    references cases

GO

create sequence twist_marks_id_seq
    minvalue 1
    start with 1
    increment by 50
    cache 5

GO

alter sequence twist_marks_id_seq owned by twist_marks.id

GO
