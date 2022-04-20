package iterator.unit;

import iterator.Aggregator;
import iterator.Iterator;
import obserwator.Observer;

import java.util.ArrayList;

import static iterator.unit.UnitNames.*;

public class Units extends Aggregator {
    private final ArrayList<Unit> units;
    private final int numberOfUnits = 10;

    public Units() {
        units = new ArrayList<>();
        this.createUnits();
    }

    private void createUnits() {
        for (int i = 0; i < this.numberOfUnits; ++i) {
            Unit unit = new Unit(UNIT_NAMES[i], UNIT_CORDS[i]);
            units.add(unit);
        }
    }

    public void subscribeAllUnit(Observer observer) {
        for (Unit unit : units) {
            unit.SubscribeAllVehiclesTo(observer);
        }
    }

    @Override
    public Iterator iterator() {
        return new UnitIterator(units);
    }
}
