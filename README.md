# Twister
## Model sheme:

```mermaid
erDiagram
ACCOUNT_NUMBER {
    varchar(64) number PK
}

CASE_SLOT_NAME {
    varchar(64) name PK
}

GENERAL_ACCOUNT_NAME {
    varchar(64) name PK
}

INVENTORIES_NAME {
    varchar(64) name PK
}

TWIST_NUMBER {
    varchar(64) number PK
}

GENERAL_ACCOUNTS ||--|| GENERAL_ACCOUNT_NAME : "named"
GENERAL_ACCOUNTS {
    bigint id PK
    varchar(64) name UK, FK
    varchar(64) user_uuid UK
    varchar(64) nickname UK
    timestamp(6) modified_by
    timestamp(6) created_on
}

ACCOUNTS }|--|| MONEY_TYPE : "typed"
ACCOUNTS }|--|| GENERAL_ACCOUNTS : "has"
ACCOUNTS ||--|| ACCOUNT_NUMBER : "numerated"
ACCOUNTS {
    bigint id PK
    varchar(64) number FK, UK
    numeric amount
    integer money_type_id FK
    bigint general_account_id FK
    timestamp(6) modified_by
    timestamp(6) created_on
}

INVENTORIES }|--|| GENERAL_ACCOUNTS : "has"
INVENTORIES ||--|| INVENTORIES_NAME : "named"
INVENTORIES {
    bigint id PK
    varchar(64) name UK, FK
    bigint general_account_id FK
    timestamp(6) modified_by
    timestamp(6) created_on
}

INVENTORY_SLOTS }|--|| INVENTORIES : "has"
INVENTORY_SLOTS }|--|| ITEM : "in"
INVENTORY_SLOTS {
    bigint id PK
    integer quantity_item
    bigint inventory_id FK
    bigint item_id FK
}

CASES }|--|| MONEY_TYPE : "typed"
CASES {
    bigint id PK
    varchar(64) name UK
    integer money_type_id FK
    numeric price
    numeric cooldown
    varchar(64) visible_name
    varchar(1000) description
    timestamp(6) modified_by
    timestamp(6) created_on
}

CASE_SLOTS ||--|| CASE_SLOT_NAME : "named"
CASE_SLOTS }|--|| CASES : "has"
CASE_SLOTS }|--|| ITEM : "in"
CASE_SLOTS {
    bigint id PK
    varchar(64) name UK, FK
    integer quantity_item
    bigint case_id FK
    bigint item_id FK
    numeric percentage_wining
}

ITEM {
    bigint id PK
    varchar(64) name UK, FK
    varchar(64) visible_name
    timestamp(6) modified_by
    timestamp(6) created_on
}

ITEM_MONEY }|--|| MONEY_TYPE : "typed"
ITEM_MONEY ||--|| ITEM : "inheritor"
ITEM_MONEY {
    bigint item_money_id PK, FK
    integer money_type_id FK
    numeric cost
}

ITEM_TRASH ||--|| ITEM : "inheritor"
ITEM_TRASH {
    bigint item_trash_id PK, FK
}

MONEY_TYPE {
    integer id PK
    varchar(64) name UK, FK
    varchar(64) pathToIcon
    timestamp(6) modified_by
    timestamp(6) created_on
}

TWIST_MARKS }|--|| CASES : "grouped"
TWIST_MARKS }|--|| GENERAL_ACCOUNTS : "grouped"
TWIST_MARKS {
    integer id PK
    bigint twist_case_id FK
    bigint general_account_id FK
    boolean consider
    timestamp(6) updated_on
    timestamp(6) created_on
}

TWISTES }|--|| CASES : "log"
TWISTES }|--|| GENERAL_ACCOUNTS : "log"
TWISTES }|--|| ACCOUNTS : "log"
TWISTES }|--|| ITEM : "log"
TWISTES ||--|| TWIST_NUMBER : "numerated"
TWISTES {
    bigint id PK
    varchar(64) number UK, FK
    bigint account_id FK
    bigint case_id FK
    bigint general_account_id FK
    integer quantity_item
    bigint item_id FK
    timestamp(6) created_on
}
```