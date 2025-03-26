package task2_9_2;

public class Boat extends Transport {
    public Boat(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " плывёт по реке.");
    }
}