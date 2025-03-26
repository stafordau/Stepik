package task2_9_2;

public class SteamLocomotive extends Transport implements SteamPowered {
    public SteamLocomotive(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " движется на паровой тяге.");
    }

    @Override
    public void startBoiler() {
        System.out.println(name + ": Запуск парового котла.");
    }

    @Override
    public void stopBoiler() {
        System.out.println(name + ": Остановка парового котла.");
    }
}