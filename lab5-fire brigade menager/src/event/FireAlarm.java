package event;

public class FireAlarm implements IAlarm {
    private final boolean isFalseAlarm;
    private final double[] location;

    public FireAlarm(double[] location, boolean isFalseAlarm) {
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
        return "Pożar";
    }

    @Override
    public int getRequiredCarAmount() {
        return 3;
    }
}
