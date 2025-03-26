package task2_9_2;

public class Bus extends Transport implements PublicTransport {
    public Bus(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " едет по маршруту.");
    }

    @Override
    public void boardPassengers(int passengers) {
        System.out.println("Автобус принял " + passengers + " пассажиров.");
    }

    @Override
    public void stopAtStation(String station) {
        System.out.println("Автобус остановился на станции " + station);
    }
}