package task2_9_2;

public class Airplane extends Transport {
    public Airplane(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " летит в небе.");
    }
}