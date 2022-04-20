package sample.person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sample.stan.IState;
import sample.stan.InfectedAndWithSymptomsPerson;
import sample.stan.InfectedAndWithoutSymptomsPerson;
import sample.stan.SensitiveAndHealthyPerson;
import sample.vectors.Vector2DPolarInheritance;

import java.util.Random;

@Setter
@Getter
@Builder
@AllArgsConstructor(staticName = "of")
public class Person {

    private IState state;
    private Vector2DPolarInheritance vector;
    private int id;
    private double speed;
    private double angleX, angleY;

    private final Random random = new Random();

    public Person getPerson(){
        return Person.of(state, vector, id, speed, angleX, angleY);
    }

    public Person(double x, double y, int id, double chanceOfInfection) {
        this.vector = new Vector2DPolarInheritance(x, y);
        this.speed = initNewSpeed();
        this.angleX = random.nextDouble() * 360.;
        this.id = id;
        this.state = random.nextDouble() > chanceOfInfection ? new SensitiveAndHealthyPerson() : random.nextBoolean() ?
                new InfectedAndWithoutSymptomsPerson() : new InfectedAndWithSymptomsPerson();
    }

    public void setState(IState state) {
        this.state = state;
    }

    public double[] movePerson(int width, int height) {
        double[] cords = this.vector.getComponents();
        this.drawChancesOfChangingDirection();
        this.drawChancesOfChangingSpeed();

        if (cords[0] >= width || cords[0] <= 0.) {
            this.angleX *= -1.;
            if (random.nextBoolean())
                this.resetPerson(width, height);
        }
        this.vector.setX(cords[0] + 2. * this.speed * Math.sin(Math.toRadians(this.angleX)));

        if (cords[1] >= height|| cords[1] <= 0. ) {
            this.angleY *= -1.;
            if (random.nextBoolean())
                this.resetPerson(width, height);
        }
        this.vector.setY(cords[1] + 2. * this.speed * Math.sin(Math.toRadians(this.angleY)));

        return this.vector.getComponents();
    }

    private void drawChancesOfChangingDirection() {
        double chances = this.random.nextDouble();
        if (chances > 0.99) {
            this.angleX = this.random.nextDouble() * 360.;
            if (this.angleX >= 0. && this.angleX <= 90.)
                this.angleY = 90. - this.angleX;
            else if (this.angleX > 90. && this.angleX <= 180.)
                this.angleY = -90. + this.angleX;
            else if (this.angleX > 180. && this.angleX <= 270.)
                this.angleY = 180. - this.angleX;
            else
                this.angleY = 270. - this.angleX;
        }
    }

    private void drawChancesOfChangingSpeed() {
        double chances = this.random.nextDouble();
        if (chances > 0.98)
            this.speed = this.initNewSpeed();
    }

    private double initNewSpeed() {
        return this.random.nextDouble() * 0.2 + 0.05;
    }

    private void resetPerson(double WIDTH, double HEIGHT) {
        this.state = random.nextDouble() > 0.1 ? new SensitiveAndHealthyPerson() : random.nextBoolean() ?
                new InfectedAndWithoutSymptomsPerson() : new InfectedAndWithSymptomsPerson();

        double rand = random.nextDouble();
        if (rand < 0.25) {
            this.vector.setX(0);
            this.vector.setY(random.nextDouble() * HEIGHT);
            this.angleX = random.nextDouble() * 170. + 275.;
        } else if (rand < 0.5) {
            this.vector.setX(WIDTH);
            this.vector.setY(random.nextDouble() * HEIGHT);
            this.angleX = random.nextDouble() * 170. + 95.;
        } else if (rand < 0.75) {
            this.vector.setX(random.nextDouble() * WIDTH);
            this.vector.setY(0.);
            this.angleX = random.nextDouble() * 170. + 185.;
        } else {
            this.vector.setX(random.nextDouble() * WIDTH);
            this.vector.setY(HEIGHT);
            this.angleX = random.nextDouble() * 170. + 5.;
        }
    }

    public void handle(Person person, double distance){
        this.state.handle(this, person, distance);
    }


}
