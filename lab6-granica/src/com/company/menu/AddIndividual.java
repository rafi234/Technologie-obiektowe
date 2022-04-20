package com.company.menu;

public class AddIndividual implements IAction {
    @Override
    public void action() {
        Menu.proxy.readData();
    }
}