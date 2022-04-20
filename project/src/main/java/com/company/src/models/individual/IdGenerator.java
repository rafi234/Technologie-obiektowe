package com.company.src.models.individual;

public class IdGenerator {
    private static int ID = 0;

    public static int getID() {
        return ID++;
    }
}
