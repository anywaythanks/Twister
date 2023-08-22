package com.example.twisterclient.models;

public enum TypesItem {
    MONEY(Constants.MONEY_NAME), TRASH(Constants.TRASH_NAME);

    TypesItem(String name) {
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
