package stan;

import iterator.car.Car;

import java.util.Objects;

public class InActionCarState implements ICarState {
    private long timeInAction = -1L;
    private long startActionTime;


    @Override
    public void handle(Car car) {
        if (this.timeInAction == -1L) {
            this.initTimeRanges();
        }

        if (System.currentTimeMillis() - startActionTime >= timeInAction) {
            car.setState(new ReturningCarState());
            System.out.println(car.getName() + " zakończył akcje. Powrót do bazy...");
            this.timeInAction = -1L;
        }
    }

    private void initTimeRanges() {
        this.timeInAction = TimeRangeGetter.getTimeFromGivenRange(5., 25.);
        this.startActionTime = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        return "InActionCar";
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
