import static java.lang.Math.sqrt;

public class Vector2D implements  IVector{
    private final double x;
    private final double y;


    Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public double abs() {
        return sqrt(this.x * this.x + this.y *this.y);
    }

    @Override
    public double cdot(IVector vector) {
        double[] tab = vector.getComponents();
        return this.x * tab[0] + this.y * tab[1];
    }

    @Override
    public double[] getComponents() {
        double[] tab = new double[2];
        tab[0] = this.x;
        tab[1] = this.y;
        return tab;
    }
}
