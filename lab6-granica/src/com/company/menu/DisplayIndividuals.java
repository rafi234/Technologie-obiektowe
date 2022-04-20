package com.company.menu;

import com.company.individual.Individual;
import com.company.individual.IndividualContainer;

public class DisplayIndividuals implements IAction {
    @Override
    public void action() {
        try {
            for (Individual individual :
                    IndividualContainer.getIndividualContainer().getIndividualList()) {
                System.out.println(individual.toString());
            }
        } catch (NullPointerException e) {
            System.out.println("Nie dodałeś jeszcze żadnej osoby!");
        }
    }
}