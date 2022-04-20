package com.company.src.models.individual;

import com.company.src.models.individual.proxy.PeopleContainerManager;

public class PeopleContainer {
    private static PeopleContainerManager peopleContainer = null;

    private PeopleContainer(){
        peopleContainer = new PeopleContainerManager();
    }

    public static PeopleContainerManager getPeopleContainer() {
        if(peopleContainer == null)
            new PeopleContainer();
        return peopleContainer;
    }
}
