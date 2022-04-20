package stan;

import iterator.car.Car;

import java.util.Objects;

public class ReturningCarState implements ICarState {
    private long timeForReturn = -1L;
    private long startActionTime;


    @Override
    public void handle(Car car) {
        if (this.timeForReturn == -1L) {
            this.initTimeRanges();
        }
        if (System.currentTimeMillis() - startActionTime >= timeForReturn) {
            car.setState(new FreeCarState());
            System.out.println(car.getName() + " powrócił do bazy.");
            this.timeForReturn = -1L;
        }
    }

    private void initTimeRanges() {
        this.timeForReturn = TimeRangeGetter.getTimeFromGivenRange(0., 3.);
        this.startActionTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "ReturningCar";
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

