package task2_9_1;

public class Pentagon extends Figure {
    private double side;

    public Pentagon(double side) {
        super("Пятиугольник");
        this.side = side;
    }

    @Override
    public double getPerimeter() {
        return 5 * side;
    }

    @Override
    public double getArea() {
        return (5 * side * side) / (4 * Math.tan(Math.PI / 5));
    }
}
