import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        Polar2DAdapter polar2DAdapter = new Polar2DAdapter(new Vector2D(5.3, -0.43));
        Vector2DPolarInheritance polarInheritance = new Vector2DPolarInheritance(4.3, 7.4);
        Vector3DDecorator vector3DDecorator = new Vector3DDecorator(new Vector2D(-1.3, 9.32), 7.5);

        System.out.println("Wektory w układzie kartezjańskim:");
        System.out.println("a = " + arrayToString(polar2DAdapter) + "\n" +
                "b = " + arrayToString(polarInheritance) + "\n" +
                "c = " + arrayToString(vector3DDecorator));

        System.out.println("\nWektory 2D w układzie biegunowym:");
        System.out.println("a = " + getPolar2DAdapter(polar2DAdapter) + "\n" +
                "b = " + getVector2DPolarInheritance(polarInheritance));

        System.out.println("\nWyniki iloczynu skalarnego dla wszystkich możliwych kombinacji wektorów:"
                + "\naob = " + polar2DAdapter.cdot(polarInheritance) +
                "\naoc = " + polar2DAdapter.cdot(vector3DDecorator.getSrcV()) +
                "\nboc = " + polarInheritance.cdot(vector3DDecorator.getSrcV()));

        System.out.println("\nWyniki iloczynu wektorowego dla wszystkich możliwych kombinacji wektorów:\n" +
                "cxa = " + arrayToString(vector3DDecorator.cross(polar2DAdapter)) +
                "\ncxb = " + arrayToString(vector3DDecorator.cross(polarInheritance)));

    }

    public static String getPolar2DAdapter(IPolar2D polar2D) {
        return "[r, alfa] = ["
                + polar2D.abs() + ", "
                + Math.toDegrees(polar2D.getAngle()) + "]";
    }

    public static String getVector2DPolarInheritance(Vector2DPolarInheritance vector2DPolarInheritance) {
        return "[r, alfa] = ["
                + vector2DPolarInheritance.abs() + ", "
                + Math.toDegrees(vector2DPolarInheritance.getAngle()) + "]";
    }

    public static String arrayToString(IVector vector) {
        return Arrays.toString(vector.getComponents());
    }
}
