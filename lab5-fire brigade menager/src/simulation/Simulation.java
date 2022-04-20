package simulation;

import event.EventGenerator;
import iterator.unit.Units;
import obserwator.Observer;
import strategia.FindNearestUnitStrategy;
import strategia.IStrategy;

import java.util.Random;

public class Simulation {
    private static Units units;
    private static IStrategy strategy;
    private static Observer observer;
    private static EventGenerator eventGenerator;
    private static Random random;

    public static void run() {
        random = new Random();
        units = new Units();
        strategy = new FindNearestUnitStrategy();
        observer = new Observer(strategy, units);
        units.subscribeAllUnit(observer);
        eventGenerator = new EventGenerator();

        while (true) {
            if (eventGenerator.whenNextAlarm()) {
                observer.alarm(eventGenerator.generateEvent());
            }
            observer.handleAll();
        }
    }
}
