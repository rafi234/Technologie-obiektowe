package obserwator;

import event.IAlarm;
import iterator.unit.Units;
import lombok.Getter;
import strategia.IStrategy;

import java.util.ArrayList;
import java.util.Arrays;

import static obserwator.LocalThreat.getLocalThreatName;

@Getter
public class Observer {
    private final Units units;
    private IStrategy strategy;

    private ArrayList<IObserver> observersList;


    public Observer(IStrategy strategy, Units units) {
        this.observersList = new ArrayList<>();
        this.units = units;
        this.strategy = strategy;
    }

    public void addObserver(IObserver observer) {
        observersList.add(observer);
    }

    public void notify(IObserver observer, boolean isFalseAlarm) {
        if (observersList.contains(observer)) {
            observer.send(isFalseAlarm);
        }
    }

    public void alarm(IAlarm eventAlarm) {
        System.out.println("\n\tZgłoszenie zdarzenia:");
        System.out.print("\tW lokalizacji " + Arrays.toString(eventAlarm.getLocation()) + " doszło do ");
        if(eventAlarm.getAlarmName().equals("MZ"))
            System.out.println("(MZ) " + getLocalThreatName() + "\n");
        else System.out.println("Pożaru.\n");

        executeStrategy(eventAlarm);
    }

    void executeStrategy(IAlarm event) {
        strategy.execute(this, event);
    }

    public void handleAll() {
        for (IObserver observer : observersList) {
            observer.handle();
        }
    }
}
