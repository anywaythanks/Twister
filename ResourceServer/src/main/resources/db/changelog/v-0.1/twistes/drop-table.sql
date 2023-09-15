alter table if exists twistes
    drop constraint FK_Twistes_Accounts

GO

alter table if exists twistes
    drop constraint FK_Twistes_GeneralAccounts

GO

alter table if exists twistes
    drop constraint FK_Twistes_TwistNumber

GO

alter table if exists twistes
    drop constraint FK_Twistes_Cases

GO

alter table if exists twistes
    drop constraint FK_Twistes_Items

GO

drop table twistes

GO