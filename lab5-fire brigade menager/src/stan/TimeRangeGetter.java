package stan;

import java.util.Random;

public class TimeRangeGetter {
    public static Random random = new Random();

    public static long getTimeFromGivenRange(double lowerRange, double range) {
        return (long) ((random.nextDouble() * range + lowerRange) * 1000.);
    }
}
