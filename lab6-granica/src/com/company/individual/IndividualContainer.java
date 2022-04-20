package com.company.individual;

import com.company.individual.coordinates.Coordinates;
import com.company.pyłek.IFlyweight;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class IndividualContainer {

    private static IndividualContainer individualContainer;
    @Getter
    private List<Individual> individualList;

    private IndividualContainer() {
        individualList = new ArrayList<>();
    }

    public static IndividualContainer getIndividualContainer() {
        if (individualContainer == null)
            individualContainer = new IndividualContainer();
        return individualContainer;
    }

    public void addToContainer(Coordinates coordinates,
                               IFlyweight nameFlyweight,
                               ArrayList<IFlyweight> surnameFlyweight) {
        individualList.add(new Individual(coordinates, surnameFlyweight, nameFlyweight));
    }
}