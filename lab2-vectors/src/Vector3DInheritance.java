import static java.lang.Math.sqrt;

public class Vector3DInheritance extends Vector2D {
    private final double z;

    Vector3DInheritance(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public Vector3DInheritance cross(IVector vector) {
        double[] tab1 = super.getComponents();
        double[] tab2 = checkIfVectorIs3D(vector);
        return new Vector3DInheritance(tab1[1] * tab2[2] - this.z * tab2[1],
                this.z * tab2[0] - tab1[0] * tab2[2],
                tab1[0] * tab2[1] - tab1[1] * tab2[0]);
    }

    @Override
    public double abs() {
        double[] tab = this.getComponents();
        return sqrt(tab[0] * tab[0] + tab[1] * tab[1] + tab[2] * tab[2]);
    }

    @Override
    public double cdot(IVector vector) {
        double[] tab1 = super.getComponents();
        double[] tab2 = vector.getComponents();
        return tab1[0] * tab2[0] + tab1[1] * tab2[1] + this.z * tab2[2];
    }

    @Override
    public double[] getComponents() {
        double[] tab = new double[3];
        tab[0] = super.getComponents()[0];
        tab[1] = super.getComponents()[1];
        tab[2] = this.z;
        return tab;
    }

    public IVector getSrcV() {
        double[] tab = super.getComponents();
        return new Vector2D(tab[0], tab[1]);
    }

    private double[] checkIfVectorIs3D(IVector vector) {
        double[] tab = new double[3];
        if (vector.getComponents().length == 2) {
            tab[0] = vector.getComponents()[0];
            tab[1] = vector.getComponents()[1];
            tab[2] = 0.;
            return tab;
        } else
            return vector.getComponents();

    }
}
