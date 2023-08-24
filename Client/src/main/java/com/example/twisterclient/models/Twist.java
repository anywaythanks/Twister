package com.example.twisterclient.models;

public class Twist {
    String caseName;

    protected Twist() {
    }

    public Twist(String caseName) {
        this.caseName = caseName;
    }

    public String getCaseName() {
        return caseName;
    }
}