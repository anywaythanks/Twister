# Twister
Service with OAuth2 authorization. With the ability to store and transfer play money and items, as well as the possibility of issuing and remitting play money through twists.
## Model sheme
### Autogenerated
Tables to maintain sequnced and autogenerate names and numbers.
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

INVENTORIES ||--|| INVENTORIES_NAME : "named"
INVENTORIES {
bigint id PK
varchar(64) name UK, FK
bigint general_account_id FK
timestamp(6) modified_by
timestamp(6) created_on
}

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

CASE_SLOTS ||--|| CASE_SLOT_NAME : "named"
CASE_SLOTS {
bigint id PK
varchar(64) name UK, FK
integer quantity_item
bigint case_id FK
bigint item_id FK
numeric percentage_wining
}
```

### User possessions

#### Interserver relationship

A separate auth server for OAuth2 forces a cross-server connection via the uuid that will be in the jwt.
```mermaid
erDiagram

USER_PRINCIPAL {
varchar(64) user_uuid UK
}

GENERAL_ACCOUNTS }|--|| USER_PRINCIPAL : "has"
GENERAL_ACCOUNTS {
bigint id PK
varchar(64) name UK, FK
varchar(64) user_uuid UK
varchar(64) nickname UK
timestamp(6) modified_by
timestamp(6) created_on
}
```

#### General account
Each general account can own accounts for a certain type of money and inventory for storing items.

```mermaid
erDiagram

GENERAL_ACCOUNTS {
bigint id PK
varchar(64) name UK, FK
varchar(64) user_uuid UK
varchar(64) nickname UK
timestamp(6) modified_by
timestamp(6) created_on
}

ACCOUNTS }|--|| GENERAL_ACCOUNTS : "has"
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
INVENTORIES {
bigint id PK
varchar(64) name UK, FK
bigint general_account_id FK
timestamp(6) modified_by
timestamp(6) created_on
}
```
#### Inventory
So each inventory contains slots for some item.
```mermaid
erDiagram

INVENTORIES {
bigint id PK
varchar(64) name UK, FK
bigint general_account_id FK
timestamp(6) modified_by
timestamp(6) created_on
}

INVENTORY_SLOTS }|--|| INVENTORIES : "has"
INVENTORY_SLOTS {
bigint id PK
integer quantity_item
bigint inventory_id FK
bigint item_id FK
}
```
##### Inventory slot
Each slot contains both the number of items and the items themselves.
```mermaid
erDiagram

INVENTORY_SLOTS }|--|| ITEM : "in"
INVENTORY_SLOTS {
bigint id PK
integer quantity_item
bigint inventory_id FK
bigint item_id FK
}

ITEM {
bigint id PK
varchar(64) name UK, FK
varchar(64) visible_name
timestamp(6) modified_by
timestamp(6) created_on
}
```
#### Account
Each account is tied to some type of money.

```mermaid
erDiagram

ACCOUNTS }|--|| MONEY_TYPE : "typed"
ACCOUNTS {
bigint id PK
varchar(64) number FK, UK
numeric amount
integer money_type_id FK
bigint general_account_id FK
timestamp(6) modified_by
timestamp(6) created_on
}

MONEY_TYPE {
integer id PK
varchar(64) name UK, FK
varchar(64) pathToIcon
timestamp(6) modified_by
timestamp(6) created_on
}
```

#### Full
So, the full ownership scheme:
```mermaid
erDiagram
USER_PRINCIPAL {
varchar(64) user_uuid UK
}

GENERAL_ACCOUNTS }|--|| USER_PRINCIPAL : "has"
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

ITEM {
bigint id PK
varchar(64) name UK, FK
varchar(64) visible_name
timestamp(6) modified_by
timestamp(6) created_on
}

MONEY_TYPE {
integer id PK
varchar(64) name UK, FK
varchar(64) pathToIcon
timestamp(6) modified_by
timestamp(6) created_on
}
```

### Infrastructure around
#### Item
There are currently two types of items: trash and money. The key difference is that ItemMoney has a price in the form of some type of money.
```mermaid
erDiagram

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
```
#### Case
The case consists of slots with items and a certain chance of falling out, as well as the cost in the equivalent of a certain type of money.
```mermaid
erDiagram
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

MONEY_TYPE {
integer id PK
varchar(64) name UK, FK
varchar(64) pathToIcon
timestamp(6) modified_by
timestamp(6) created_on
}
```
#### Twist
TWIST - logging table for each twist case. TWIST_MARK - conditional trigger table to account for case cooldowns. There is a condition whether it is worth considering this mark at all.
```mermaid
erDiagram
ITEM {
bigint id PK
varchar(64) name UK, FK
varchar(64) visible_name
timestamp(6) modified_by
timestamp(6) created_on
}

GENERAL_ACCOUNTS {
bigint id PK
varchar(64) name UK, FK
varchar(64) user_uuid UK
varchar(64) nickname UK
timestamp(6) modified_by
timestamp(6) created_on
}

ACCOUNTS {
bigint id PK
varchar(64) number FK, UK
numeric amount
integer money_type_id FK
bigint general_account_id FK
timestamp(6) modified_by
timestamp(6) created_on
}

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

### Full
Full scheme:
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