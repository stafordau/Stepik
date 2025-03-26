package task2_9_2;

public class Train extends Transport implements PublicTransport {
    public Train(String name, int capacity) {
        super(name, capacity);
    }

    @Override
    public void move() {
        System.out.println(name + " движется по рельсам.");
    }

    @Override
    public void boardPassengers(int passengers) {
        System.out.println("Поезд принял " + passengers + " пассажиров.");
    }

    @Override
    public void stopAtStation(String station) {
        System.out.println("Поезд остановился на станции " + station);
    }
}