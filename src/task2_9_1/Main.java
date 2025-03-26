package task2_9_1;

public class Main {
    public static void main(String[] args) {
        EquilateralTriangle triangle = new EquilateralTriangle(5);
        Square square = new Square(4);
        Oval oval = new Oval(6, 3);

        triangle.display();
        System.out.println("Площадь: " + triangle.getArea() + ", Периметр: " + triangle.getPerimeter());

        square.display();
        System.out.println("Площадь: " + square.getArea() + ", Периметр: " + square.getPerimeter());

        oval.display();
        System.out.println("Площадь: " + oval.getArea() + ", Периметр: " + oval.getPerimeter());
    }
}
