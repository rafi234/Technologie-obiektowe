package obserwator;

import static stan.TimeRangeGetter.random;

public class LocalThreat {
    public static String[] LocalThreats = {"Uwięzienia kota na drzewie.",
            "spadnięcia drzewa na drogę.",
            "Zawalenia budynku.",
            "Wypadku samochodowego na autostradzie.",
            "Wypadku samochodowego w mieście.",
            "Powodzi.",
            "Nielegalnego protestu.",
            "Trzęsienia ziemi."
    };

    public static String getLocalThreatName() {
        return LocalThreats[random.nextInt(LocalThreats.length)];
    }
}
