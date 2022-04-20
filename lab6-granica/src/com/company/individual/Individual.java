package com.company.individual;

import com.company.individual.coordinates.Coordinates;
import com.company.pyłek.IFlyweight;

import java.util.ArrayList;
import java.util.Arrays;


public class Individual {
    private final Coordinates coordinates;
    private final ArrayList<IFlyweight> surname;
    private final IFlyweight name;

    public Individual(Coordinates coordinates, ArrayList<IFlyweight> surnameFlyweight, IFlyweight nameFlyweight) {
        this.coordinates = coordinates;
        this.surname = surnameFlyweight;
        this.name = nameFlyweight;
    }

    @Override
    public String toString() {
        return "Nazwisko: " + surnameToString() + "\n" +
                "Imię: " + name.getData() + "\n" +
                "Współrzędne: " + Arrays.toString(coordinates.getCords()) + "\n";
    }

    public String surnameToString() {
        String fullSurname = "";
        for (IFlyweight flyweight : surname)
            fullSurname += flyweight.getData() + " ";

        return fullSurname;
    }
}
