package event.location;

import java.util.Random;

import static event.location.Constants.*;


public class Location {
    private static final Random random = new Random();

    public static double[] getRandomCords() {
        double[] cords = new double[2];
        cords[0] = random.nextDouble() * Y_RANGE + 49.95855025648944;
        cords[1] = random.nextDouble() * X_RANGE + 19.688292482742394;
        return cords;
    }
}
