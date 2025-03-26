package task2_9_1;

public class Oval extends Figure {
    private double majorAxis, minorAxis;

    public Oval(double majorAxis, double minorAxis) {
        super("Овал");
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * Math.sqrt((majorAxis * majorAxis + minorAxis * minorAxis) / 2);
    }

    @Override
    public double getArea() {
        return Math.PI * majorAxis * minorAxis;
    }
}
