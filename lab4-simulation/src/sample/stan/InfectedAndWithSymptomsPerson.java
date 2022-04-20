package sample.stan;

import sample.person.Person;

import java.util.Objects;
import java.util.Random;

public class InfectedAndWithSymptomsPerson implements IState {

    private static final Random random = new Random();

    private long startOfInfection = 0L;
    private final long durationOfInfection = (long) ((random.nextDouble() * 10) + 20) * 1000L;

    @Override
    public void handle(Person person1, Person person2, double distance) {
        if (startOfInfection == 0L)
            startOfInfection = System.currentTimeMillis();
       else if (System.currentTimeMillis() - startOfInfection >= durationOfInfection) {
            person1.setState(new ResistantPerson());
        }
    }

    @Override
    public String toString() {
        return "InfectedAndWithSymptomsPerson";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IState that = (IState) o;
        return Objects.equals(this.toString(), that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
