package task2_9_1;

public class IsoscelesTriangle extends Triangle {
    public IsoscelesTriangle(double base, double side) {
        super("Равнобедренный треугольник", base, side, side);
    }

    @Override
    public double getArea() {
        double height = Math.sqrt(a * a - (b / 2) * (b / 2));
        return (b * height) / 2;
    }
}
