package event;

import event.location.Location;

import static stan.TimeRangeGetter.random;

public class EventGenerator {

    private long timeToNextAlarm;
    private long whenLastAlarmWas = 0L;

    public EventGenerator() {
    }

    public IAlarm generateEvent() {
        boolean isFalseAlarm = random.nextDouble() < 0.05;
        double[] incidentLocation = Location.getRandomCords();

        whenLastAlarmWas = System.currentTimeMillis();
        timeToNextAlarm = Math.abs(random.nextLong() % 5000L) + 2000L;

        if (random.nextDouble() > 0.7)
            return new FireAlarm(incidentLocation, isFalseAlarm);
        else
            return new LocalThreatAlarm(incidentLocation, isFalseAlarm);
    }

    public boolean whenNextAlarm() {
        return System.currentTimeMillis() - whenLastAlarmWas >= timeToNextAlarm;
    }
}
