package sample.pamiatka;

import lombok.Getter;
import lombok.Setter;
import sample.person.Person;

import java.util.ArrayList;

@Getter
@Setter
public class Memento {
    private int counter;
    private ArrayList<Person> population;
    private  int keyCounter;

    public Memento(int counter, int keyCounter, ArrayList<Person> population){
        this.counter = counter;
        this.keyCounter = keyCounter;
        this.population = population;
    }
}
