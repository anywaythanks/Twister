alter table if exists accounts
    drop constraint FK_Accounts_MoneyType

GO

alter table if exists accounts
    drop constraint FK_Accounts_GeneralAccounts

GO

alter table if exists accounts
    drop constraint FK_Accounts_AccountNumber

GO

drop table accounts

GO