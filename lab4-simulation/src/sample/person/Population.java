package sample.person;

import javafx.scene.paint.Color;
import sample.View.SimulationViewManager;
import sample.stan.ResistantPerson;
import sample.stan.SensitiveAndHealthyPerson;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class Population {
    public static ArrayList<Person> populationList = new ArrayList<>();
    private final int numberOfPopulation;
    public static int counter;

    private final int OBJECT_RADIUS = 15;
    private final double CHANCES_OF_INFECTION;

    private final Random random = new Random();

    private final int WIDTH;
    private final int HEIGHT;

    public Population(int WIDTH, int HEIGHT, double chancesOfInfection, int numberOfPopulation) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.CHANCES_OF_INFECTION = chancesOfInfection;
        this.numberOfPopulation = numberOfPopulation;
    }


    public void createPopulation() {
        for (int i = 0; i < this.numberOfPopulation; i++) {
            double[] cords = this.getCords();
            populationList.add(new Person(cords[0], cords[1], counter++, CHANCES_OF_INFECTION));
            SimulationViewManager.gc.setFill(Color.GREEN);
            SimulationViewManager.gc.fillOval(cords[0], cords[1], this.OBJECT_RADIUS, this.OBJECT_RADIUS);
        }
    }

    private double[] getCords() {
        double[] cords = new double[2];
        cords[0] = random.nextInt(WIDTH);
        cords[1] = random.nextInt(HEIGHT);
        return cords;

    }

    public void movePeople() {
        SimulationViewManager.gc.setFill(Color.BLACK);
        SimulationViewManager.gc.fillRect(0., 0., WIDTH, HEIGHT);

        for (Person person : populationList) {
            double[] newCords = person.movePerson(WIDTH, HEIGHT);
            Color color = this.getColor(person);
            SimulationViewManager.gc.setFill(color);
            SimulationViewManager.gc.fillOval(newCords[0], newCords[1], this.OBJECT_RADIUS, this.OBJECT_RADIUS);
        }
    }

    private Color getColor(Person person) {
        return person.getState().equals(new SensitiveAndHealthyPerson()) ? Color.GREEN :
                person.getState().equals(new ResistantPerson()) ? Color.BLUE : Color.RED;
    }

    public void checkIfPeopleAreCloseToEachOther() {
        for (int i = 0; i < populationList.size(); ++i) {
            for (int j = 0; j < populationList.size(); ++j) {
                if (i != j) {
                    double distance = 0.;
                    if (populationList.get(i).getState().equals(new SensitiveAndHealthyPerson())) {
                        distance = calculateDistance(populationList.get(i).getVector().getComponents()[0],
                                populationList.get(j).getVector().getComponents()[0],
                                populationList.get(i).getVector().getComponents()[1],
                                populationList.get(j).getVector().getComponents()[1]);
                    }
                    populationList.get(i).handle(populationList.get(j), distance);
                }
            }
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }

    public ArrayList<Person> copyPopulation() {
        return (ArrayList<Person>) populationList.stream().map(Person::getPerson).collect(Collectors.toList());
    }

}
