package sample.stan;

import sample.person.Person;

import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.TreeMap;


public class SensitiveAndHealthyPerson implements IState {

    private final Map<Integer, Long> peopleWithCloseContact = new TreeMap<>();

    @Override
    public void handle(Person person1, Person person2, double distance) {
        if (distance < 70. &&
                (person2.getState().equals(new InfectedAndWithoutSymptomsPerson())
                || person2.getState().equals(new InfectedAndWithSymptomsPerson())) ){
           if (!person1.getState().equals(new InfectedAndWithoutSymptomsPerson())
                    || !person1.getState().equals(new InfectedAndWithSymptomsPerson())) {
                if (personWithInfectionIsClose(person2.getId())) {
                    if (new Random().nextBoolean())
                        person1.setState(new InfectedAndWithSymptomsPerson());
                    else
                        person1.setState(new InfectedAndWithoutSymptomsPerson());
                }
            }
        } else this.setTimeTo_0s(person2.getId());
    }


    public boolean personWithInfectionIsClose(int id) {
        if (!peopleWithCloseContact.containsKey(id)) {
            peopleWithCloseContact.put(id, System.currentTimeMillis());
        } else {
            return isTimeBiggerThan3s(id);
        }
        return false;
    }

    public void setTimeTo_0s(int id) {
        this.peopleWithCloseContact.remove(id);
    }

    private boolean isTimeBiggerThan3s(int id) {
        return System.currentTimeMillis() - peopleWithCloseContact.get(id) >= 3000L;
    }

    @Override
    public String toString() {
        return "SensitiveAndHealthyPerson";
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
