package event;

public interface IAlarm {
    public boolean isFalseAlarm();

    public double[] getLocation();

    public String getAlarmName();

    public int getRequiredCarAmount();
}
