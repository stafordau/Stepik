package task2_9_2;

public class Car extends Transport {
    public Car(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " едет по дороге.");
    }
}