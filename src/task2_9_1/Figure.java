package task2_9_1;

public abstract class Figure {
    protected String name;

    public Figure(String name) {
        this.name = name;
    }

    public abstract double getArea();
    public abstract double getPerimeter();

    public void display() {
        System.out.println("Фигура: " + name);
    }
}

