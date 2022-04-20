package sample.vectors;

import static java.lang.Math.PI;
import static java.lang.Math.atan;

public class Polar2DAdapter implements IPolar2D, IVector{

    private final Vector2D srcVector;

    public Polar2DAdapter(Vector2D vector2D){
        this.srcVector = vector2D;
    }

    @Override
    public double abs() {
        return this.srcVector.abs();
    }

    @Override
    public double cdot(IVector vector) {
        return this.srcVector.cdot(vector);
    }

    @Override
    public double[] getComponents() {
        return this.srcVector.getComponents();
    }

    @Override
    public double getAngle() {
        double[] tab = this.srcVector.getComponents();
        if (tab[0] > 0. && tab[1] >= 0.) return atan(tab[1] / tab[0]);
        else if (tab[0] > 0. && tab[1] < 0.) return atan(tab[1] / tab[0]) + 2. * PI;
        else if (tab[0] < 0.) return atan(tab[1] / tab[0]) + PI;
        else if (tab[0] == 0. && tab[1] > 0.) return PI / 2.;
        else return 3. * PI / 2.;
    }
}
