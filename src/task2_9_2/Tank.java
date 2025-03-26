package task2_9_2;

public class Tank extends Transport implements MilitaryTransport {
    public Tank(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " движется по полю боя.");
    }

    @Override
    public void attack() {
        System.out.println(name + " атакует противника!");
    }

    @Override
    public void defend() {
        System.out.println(name + " защищается от вражеского огня.");
    }
}