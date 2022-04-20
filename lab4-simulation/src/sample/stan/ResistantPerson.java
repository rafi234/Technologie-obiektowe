package sample.stan;

import sample.person.Person;

import java.util.Objects;

public class ResistantPerson implements IState {

    @Override
    public void handle(Person person1, Person person2, double distance) {

    }

    @Override
    public String toString() {
        return "ResistantPerson";
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
