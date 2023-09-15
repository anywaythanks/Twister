create table item_trash (
    item_trash_id bigint not null,
    primary key (item_trash_id)
)

GO

alter table if exists item_trash
    add constraint FK_ItemTrash_Items
    foreign key (item_trash_id)
    references items

GO