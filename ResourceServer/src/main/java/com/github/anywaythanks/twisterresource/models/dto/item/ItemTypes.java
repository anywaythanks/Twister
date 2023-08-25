package com.github.anywaythanks.twisterresource.models.dto.item;

public enum ItemTypes {
    MONEY(Constants.MONEY_NAME), TRASH(Constants.TRASH_NAME);

    ItemTypes(String name) {
        if (!name.equals(this.name()))
            throw new IllegalArgumentException();
    }

    public static class Constants {
        public static final String MONEY_NAME = "MONEY";
        public static final String TRASH_NAME = "TRASH";
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
