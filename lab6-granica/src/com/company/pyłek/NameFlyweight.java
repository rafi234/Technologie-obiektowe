package com.company.pyłek;

public class NameFlyweight implements IFlyweight {
    private final String name;

    public NameFlyweight(String name) {
        this.name = name;
    }

    @Override
    public String getData() {
        return this.name;
    }
}

