package strategia;

import event.IAlarm;
import iterator.Iterator;
import iterator.car.Car;
import iterator.unit.Unit;
import obserwator.Observer;
import stan.FreeCarState;

import java.util.LinkedList;

public class FindNearestUnitStrategy implements IStrategy {
    @Override
    public void execute(Observer observer, IAlarm alarm) {
        double[] cords = alarm.getLocation();

        int carsNeeded = alarm.getRequiredCarAmount();

        LinkedList<Unit> units = new LinkedList<>();

        while (carsNeeded > 0) {
            Iterator it = observer.getUnits().iterator();

            Unit currentUnit, nearestUnit = null;
            double minDistance = Double.MAX_VALUE;
            double currentDistance;

            while (it.hasNext()) {
                currentUnit = (Unit) it.next();
                if (units.contains(currentUnit)) continue;

                currentDistance = countDistance(cords, currentUnit);
                if (minDistance > currentDistance) {
                    minDistance = currentDistance;
                    nearestUnit = currentUnit;
                }
            }
            if (nearestUnit == null) {
                System.out.println("BRAK POJAZDÓW DO DYSPOZYCJI!!!");
                break;
            }

            units.add(nearestUnit);
            System.out.print("\tZ jednostki " + nearestUnit.getUnitName() + " do akcji wyruszyły ");
            int carsNeededLeft = checkIfCarInBaseIsAvailable(observer, nearestUnit, carsNeeded , alarm);
            System.out.println(carsNeeded - carsNeededLeft + " pojazdy.\n");
            carsNeeded = carsNeededLeft;
        }
    }


    private double countDistance(double[] alarmLocation, Unit unit) {
        return Math.sqrt((alarmLocation[0] - unit.getCords()[0]) * (alarmLocation[0] - unit.getCords()[0])
                + (alarmLocation[1] - unit.getCords()[1]) * (alarmLocation[1] - unit.getCords()[1]));
    }

    private int checkIfCarInBaseIsAvailable(Observer observer, Unit nearestBase, int carsNeeded, IAlarm alarm) {
        Iterator it = nearestBase.iterator();
        while (carsNeeded > 0 && it.hasNext()) {
            Car car = (Car) it.next();
            if (car.getState().equals(new FreeCarState())) {
                    observer.notify(car, alarm.isFalseAlarm());
                    --carsNeeded;
            }
        }
        return carsNeeded;
    }
}
