package stan;

import iterator.car.Car;

import java.util.Objects;

public class FreeCarState implements ICarState {
    @Override
    public void handle(Car car) {

    }

    @Override
    public String toString() {
        return "FreeCar";
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
