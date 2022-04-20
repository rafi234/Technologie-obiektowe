package com.company.menu;

public class IncorrectCommand implements IAction {

    @Override
    public void action() {
        System.out.println("Podano złą komendę!");
    }
}
