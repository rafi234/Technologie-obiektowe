import org.jetbrains.annotations.NotNull;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    private final static String menu = "Co chcesz zrobić?\n" +
            "1 - Zamień z lub na złotówki.\n" +
            "2 - Zamień dwie dowolne waluty.\n" +
            "3 - Pokaż listę wszystkich walut.\n" +
            "4 - Wyjdź z aplikacji.";

    private final static String secondMenu = "Czy chcesz zamienić złotówki na obcą walutę?\n" +
            "1 - Złotówki na obcą walutę.\n" +
            "2 - Obca waluta na złotówki.";

    public static void main(String[] args) {
        Encoder encoder = Encoder.getInstance();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println(menu);
            String switchFlag = scanner.nextLine();
            double value = -1.;
            switch (switchFlag) {
                case "1":

                    switchFlag = getSecondMenu(scanner);
                    Currency x = getForeignCurrency(encoder, scanner, "Podaj kod obcej waluty: ");
                    value = setValue(value, scanner);
                    String currencyCode = (switchFlag.equals("1")) ? x.getKod_waluty() : "PLN";
                    System.out.println("Otrzymana wartość jest równa: " +
                            roundNumber_2(setFinalValue(value, switchFlag, x)) + " " +
                            currencyCode);
                    break;

                case "2":
                    Currency a = getForeignCurrency(encoder, scanner, "Podaj kod waluty początkowej: ");
                    Currency b = getForeignCurrency(encoder, scanner, "Podaj kod waluty końcowej: ");
                    value = setValue(value, scanner);
                    System.out.println("Otrzymana wartość jest równa: " +
                            roundNumber_2(countFinalValue(a, b, value)) + " " +
                            b.getKod_waluty());
                    break;

                case "3":
                    encoder.printList();
                    break;

                case "4":
                    System.out.println("Do widzenia, życzę miłego dnia :)");
                    return;

                default:
                    System.out.println("Nieznana komenda.\n Spróbuj ponownie.");
            }
        }
    }

    private static String getSecondMenu(Scanner scanner) {
        String switchFlag;
        while (true) {
            System.out.println(secondMenu);
            switchFlag = scanner.nextLine();

            if (!switchFlag.equals("1") && !switchFlag.equals("2"))
                System.out.println("Błędna komenda.");
            else
                break;
        }
        return switchFlag;
    }

    private static Currency getForeignCurrency(Encoder encoder, Scanner scanner, String message) {
        Currency currency;
        do {
            System.out.println(message);
            currency = encoder.findCurrency(scanner.nextLine().toUpperCase().trim());
        } while (currency == null);
        return currency;
    }

    private static double setValue(double value, Scanner scanner) {
        do {
            try {
                System.out.println("Podaj, ile pieniędzy chcesz zamienić: ");
                value = scanner.nextDouble();

            } catch (InputMismatchException e) {
                System.out.println("Niepoprawne dane.");
                scanner.nextLine();
            }
        } while (value < 0.);
        scanner.nextLine();

        return value;
    }

    private static double setFinalValue(double value, String switchFlag, Currency x) {
        if (switchFlag.equals("1"))
            return value * x.getPrzelicznik() / x.getKurs_sredni();
        else
            return value / x.getPrzelicznik() * x.getKurs_sredni();
    }

    private static double countFinalValue(@NotNull Currency a, @NotNull Currency b, double value) {
        return (a.getKurs_sredni() / a.getPrzelicznik()) / (b.getKurs_sredni() / b.getPrzelicznik()) * value;
    }

    public static double roundNumber_2(double number) {
        number *= 100.;
        number = Math.round(number);
        return number / 100.;
    }
}
