package sample.vectors;

public interface IVector {
    public double abs();
    public double cdot(IVector vector);
    public double[] getComponents();
}
