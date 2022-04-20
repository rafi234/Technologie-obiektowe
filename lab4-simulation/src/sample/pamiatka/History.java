package sample.pamiatka;

import sample.View.SimulationViewManager;
import sample.person.Person;
import sample.person.Population;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class History {
    private final Map<Integer, Memento> history;

    public History(){
        history = new HashMap<>();
    }

    public void addMemento(int key, ArrayList<Person> population) {
        System.out.println("Memento number " + key + " has been added." );
        history.put(key, new Memento(Population.counter, SimulationViewManager.keyCounter, population) );
    }

    public Memento getMemento(int key) {
        return history.get(key);
    }

    public int getSizeOfMemento(){
        return history.size();
    }
}

