package com.company.menu;

import com.company.posrednik.Proxy;

import static com.company.individual.coordinates.Util.readNumber;

public class Menu {
    private static final String menu = "\nCo chcesz zrobić?\n" +
            "1 - Dodaj osobę.\n" +
            "2 - Wyświetl wszystkie wylegitymowane osoby.\n" +
            "3 - Wyjdź z aplikacji.";

    private final AddIndividual addIndividual;
    private final CloseApp closeApp;
    private final IncorrectCommand incorrectCommand;
    private final DisplayIndividuals displayIndividuals;
    public static Proxy proxy;

    public Menu() {
        addIndividual = new AddIndividual();
        closeApp = new CloseApp();
        incorrectCommand = new IncorrectCommand();
        displayIndividuals = new DisplayIndividuals();
        proxy = new Proxy();
    }

    public void showMenu() {
        IAction action;
        double whatToDo;
        while (true) {
            System.out.println(menu);
            whatToDo = readNumber();
            switch ((int) whatToDo) {
                case 1:
                    action = addIndividual;
                    break;
                case 2:
                    action = displayIndividuals;
                    break;
                case 3:
                    action = closeApp;
                    break;
                default:
                    action = incorrectCommand;
            }
            action.action();
        }
    }
}
