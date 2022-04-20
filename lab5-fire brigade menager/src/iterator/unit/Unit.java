package iterator.unit;

import iterator.Aggregator;
import iterator.Iterator;
import iterator.car.Car;
import iterator.car.CarIterator;
import lombok.Getter;
import lombok.Setter;
import obserwator.Observer;
import stan.FreeCarState;

@Getter
@Setter
public class Unit extends Aggregator {
    private int unitsLeft;
    private String unitName;
    private double[] cords;
    private Car[] carContainer;

    public Unit(String unitName, double[] cords){
        this.unitsLeft = 5;
        this.unitName = unitName;
        this.initCarsState();
        this.cords = cords;
    }

    private void initCarsState(){
        carContainer = new Car[unitsLeft];
        for(int i = 0; i < unitsLeft; ++i) {
            carContainer[i] = new Car("Samochód " + (i+1)  + " wyjechał z jednostki " + unitName);
            carContainer[i].setState(new FreeCarState());
        }
    }

    public void SubscribeAllVehiclesTo(Observer observer)
    {
        for (Car car: carContainer) {
            observer.addObserver(car);
        }
    }

    @Override
    public Iterator iterator() {
        return new CarIterator(carContainer);
    }
}
