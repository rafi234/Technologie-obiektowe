package com.company.pyłek;

import com.company.posrednik.Officer;

import java.util.ArrayList;

public class SurnameBuilder {
    private ArrayList<IFlyweight> fullSurname;

    public SurnameBuilder() {
        this.fullSurname = new ArrayList<>();
    }

    public void addSurnamePart(IFlyweight part) {
        fullSurname.add(0, part);
    }

    public ArrayList<IFlyweight> getData() {
        return this.fullSurname;
    }
}
