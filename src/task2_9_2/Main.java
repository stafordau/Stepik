package task2_9_2;

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus("Городской автобус", 40);
        Train train = new Train("Скоростной поезд", 200);

        bus.move();
        bus.boardPassengers(20);
        bus.stopAtStation("Центральная");

        train.move();
        train.boardPassengers(150);
        train.stopAtStation("Нагатинская станция");
    }
}