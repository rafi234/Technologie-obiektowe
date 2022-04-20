package com.company.src.models.magazine;

public class Magazine {

    private static MagazineLayout magazine = null;

    private Magazine() {
        magazine = new MagazineLayout();
    }

    public static MagazineLayout getMagazine() {
        if (magazine == null)
            new Magazine();
        return magazine;
    }
}
