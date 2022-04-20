package com.company.pyłek;


public class SurnameFlyweight implements IFlyweight {
    private final String part;

    public SurnameFlyweight(String part) {
        this.part = part;
    }

    @Override
    public String getData() {
        return this.part;
    }
}
