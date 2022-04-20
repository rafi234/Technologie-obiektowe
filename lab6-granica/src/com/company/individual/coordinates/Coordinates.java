package com.company.individual.coordinates;

public class Coordinates {
    private final double x;
    private final double y;

    public Coordinates(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double[] getCords() {
        double[] cords = new double[2];
        cords[0] = this.x;
        cords[1] = this.y;
        return cords;
    }
}
