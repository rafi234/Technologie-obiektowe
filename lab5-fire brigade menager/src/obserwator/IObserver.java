package obserwator;

import event.IAlarm;

public interface IObserver {
    public void send(boolean isFalseAlarm);

    public void handle();
}