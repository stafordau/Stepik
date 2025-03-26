package task2_9_2;

public class Steamship extends Transport implements SteamPowered {
    public Steamship(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " плывёт с помощью парового двигателя.");
    }

    @Override
    public void startBoiler() {
        System.out.println(name + ": Котёл запущен.");
    }

    @Override
    public void stopBoiler() {
        System.out.println(name + ": Котёл остановлен.");
    }
}