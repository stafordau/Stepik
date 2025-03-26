package task2_9_2;

public class FighterJet extends Transport implements MilitaryTransport {
    public FighterJet(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " выполняет боевой вылет.");
    }

    @Override
    public void attack() {
        System.out.println(name + " атакует с воздуха!");
    }

    @Override
    public void defend() {
        System.out.println(name + " выполняет манёвр уклонения.");
    }
}