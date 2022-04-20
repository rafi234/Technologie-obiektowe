package com.company.posrednik;

import com.company.individual.IndividualContainer;
import com.company.individual.coordinates.Coordinates;
import com.company.individual.coordinates.Util;
import com.company.pyłek.*;

public class Officer implements IProxy {
    IndividualContainer individualContainer = IndividualContainer.getIndividualContainer();

    @Override
    public void readData() {
        System.out.println("Podaj imię: ");
        String name = convertLine(Util.scanner.nextLine());
        FlyweightNameFactory.getNameFlyweight(name);

        SurnameBuilder surnameCreator = new SurnameBuilder();
        System.out.println("Podaj nazwisko: ");
        String surname = Util.scanner.nextLine();
        FlyweightSurnameFactory.getSurnameFlyweight(surname, surnameCreator);

        Coordinates coordinates = Util.getCoordinates();

        individualContainer.addToContainer(coordinates,
                FlyweightNameFactory.nameFactory.get(name),
                surnameCreator.getData());
    }

    public static String convertLine(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }

}
