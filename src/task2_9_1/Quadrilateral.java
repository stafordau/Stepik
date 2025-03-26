package task2_9_1;

public abstract class Quadrilateral extends Figure {
    protected double side1, side2, side3, side4;

    public Quadrilateral(String name, double side1, double side2, double side3, double side4) {
        super(name);
        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
        this.side4 = side4;
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3 + side4;
    }
}