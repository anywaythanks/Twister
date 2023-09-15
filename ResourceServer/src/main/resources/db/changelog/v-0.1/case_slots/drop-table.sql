alter table if exists case_slots
    drop constraint FK_CaseSlots_CaseSlotName

GO

alter table if exists case_slots
    drop constraint FK_CaseSlots_Cases

GO

alter table if exists case_slots
    drop constraint FK_CaseSlots_Items

GO

drop table case_slots


GO