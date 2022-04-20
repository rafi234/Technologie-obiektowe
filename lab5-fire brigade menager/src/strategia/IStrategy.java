package strategia;

import event.IAlarm;
import obserwator.Observer;

public interface IStrategy {
    public void execute(Observer observer, IAlarm alarm);
}
