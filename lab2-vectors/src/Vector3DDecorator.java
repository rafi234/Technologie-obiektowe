public class Vector3DDecorator implements IVector{

    private final IVector srcVector;
    private final double z;

    public Vector3DDecorator(IVector vector, double z){
        this.srcVector = vector;
        this.z = z;
    }

    public Vector3DDecorator cross(IVector vector){
        double[] tab1 = this.getComponents();
        double[] tab2 = checkIfVectorIs3D(vector);

        return new Vector3DDecorator(new Vector2D(tab1[1] * tab2[2] - this.z * tab2[1],
                this.z * tab2[0] - tab1[0] * tab2[2]),
                tab1[0] * tab2[1] - tab1[1] * tab2[0]);
    }

    public IVector getSrcV(){
        return this.srcVector;
    }

    @Override
    public double abs() {
        double[] tab = this.srcVector.getComponents();
        return Math.sqrt(tab[0] * tab[0] + tab[1] * tab[1] + this.z * this.z);
    }

    @Override
    public double cdot(IVector vector) {
        return this.srcVector.cdot(vector) + this.z * vector.getComponents()[2];
    }

    @Override
    public double[] getComponents() {
        double[] tab = new double[3];
        tab[0] = this.srcVector.getComponents()[0];
        tab[1]= this.srcVector.getComponents()[1];
        tab[2] = this.z;
        return tab;
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
