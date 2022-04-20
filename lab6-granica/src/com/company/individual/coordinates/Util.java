package com.company.individual.coordinates;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Util {
    public static Scanner scanner = new Scanner(System.in);

    public static Coordinates getCoordinates() {
        System.out.println("Podaj współrzędne: ");
        double[] cords = new double[2];
        cords[0] = readNumber();
        cords[1] = readNumber();
        return new Coordinates(cords[0], cords[1]);
    }

    public static double readNumber() {
        boolean flag;
        double number = -1.;
        do {
            try {
                flag = false;
                number = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Błędne dane!!\nSpróbuj ponownie: ");
                flag = true;
            }
            scanner.nextLine();
        } while (flag);

        return number;
    }
}
