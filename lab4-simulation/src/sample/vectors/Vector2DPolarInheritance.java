package sample.vectors;

import static java.lang.Math.PI;
import static java.lang.Math.atan;

public class Vector2DPolarInheritance extends Vector2D {

    public Vector2DPolarInheritance(double x, double y) {
        super(x, y);
    }

    public double getAngle() {
        double[] tab = super.getComponents();
        if (tab[0] > 0. && tab[1] >= 0.) return atan(tab[1] / tab[0]);
        else if (tab[0] > 0. && tab[1] < 0.) return atan(tab[1]/tab[0]) + 2. * PI;
        else if(tab[0] < 0. ) return atan(tab[1]/tab[0]) + PI;
        else if (tab[0] == 0. && tab[1] > 0.) return PI / 2.;
        else return 3. * PI / 2.;
    }
}

