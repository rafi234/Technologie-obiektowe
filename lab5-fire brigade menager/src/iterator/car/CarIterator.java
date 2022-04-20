package iterator.car;

import iterator.Iterator;

public class CarIterator extends Iterator {
    Car[] cars;
    int index;

    public CarIterator(Car[] cars){
        this.cars = cars;
        this.index = -1;
    }

    @Override
    public boolean hasNext() {
        return index < cars.length - 1;
    }

    @Override
    public Object next() {
        this.index++;
        return this.cars[this.index];
    }
}
