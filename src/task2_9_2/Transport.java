package task2_9_2;

public abstract class Transport {
    protected String name;
    protected int capacity;

    public Transport(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public abstract void move();

    public void display() {
        System.out.println("Транспорт: " + name + ", Вместимость: " + capacity);
    }
}