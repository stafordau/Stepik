package task2_9_1;

public class Rectangle extends Quadrilateral {
    public Rectangle(double width, double height) {
        super("Прямоугольник", width, height, width, height);
    }

    @Override
    public double getArea() {
        return side1 * side2;
    }
}
