alter table if exists twist_marks
    drop constraint FK_TwistMarks_Cases

GO

alter table if exists twist_marks
    drop constraint FK_TwistMarks_GeneralAccounts

GO

drop table twist_marks

GO