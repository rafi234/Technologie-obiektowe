package stan;

import iterator.car.Car;

import java.util.Objects;

public class ArrivingCarState implements ICarState {
    private long timeForArrival = -1L;
    private long startActionTime;

    private boolean isFalseAlarm;

    public ArrivingCarState(boolean isFalseAlarm){
        this.isFalseAlarm = isFalseAlarm;
    }

    public  ArrivingCarState(){}

    @Override
    public void handle(Car car) {
        if (this.timeForArrival == -1L) {
            this.initTimeRanges();
        }

        if (System.currentTimeMillis() - startActionTime >= timeForArrival) {
            if (!isFalseAlarm) {
                car.setState(new InActionCarState());
                System.out.println(car.getName() + " rozpoczął działanie.");

            } else {
                car.setState(new ReturningCarState());
                System.out.println(car.getName() +
                        " dotarł na miejsce zgłoszenia, jednak alarm okazał się fałszywy. Powrót pojazdu do bazy...");
                this.timeForArrival = -1L;
            }
        }
    }

    private void initTimeRanges() {
        this.timeForArrival = TimeRangeGetter.getTimeFromGivenRange(0., 3.);
        this.startActionTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ArrivingCar";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ICarState that = (ICarState) o;
        return Objects.equals(this.toString(), that.toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.toString());
    }
}
