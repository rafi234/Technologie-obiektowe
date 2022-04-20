package event;

public class LocalThreatAlarm implements IAlarm {
    private final boolean isFalseAlarm;
    private final double[] location;

    public LocalThreatAlarm(double[] location, boolean isFalseAlarm) {
        this.location = location;
        this.isFalseAlarm = isFalseAlarm;
    }

    @Override
    public boolean isFalseAlarm() {
        return isFalseAlarm;
    }

    @Override
    public double[] getLocation() {
        return location;
    }

    @Override
    public String getAlarmName() {
        return "MZ";
    }

    @Override
    public int getRequiredCarAmount() {
        return 2;
    }
}
