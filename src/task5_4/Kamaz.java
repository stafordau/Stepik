package task5_4;

public class Kamaz {
    public static int whell_count = 4;

    private int gruzopodemnost;
    private int maxSpeed;
    private String color;

    public Kamaz(int gruzopodemnost, int maxSpeed) {
        this.gruzopodemnost = gruzopodemnost;
        this.maxSpeed = maxSpeed;
        this.color = "оранжевый";
    }

    public int getGruz() {
        return gruzopodemnost;
    }

    public int getSpeed() {
        return maxSpeed;
    }

    public String getColor() {
        return color;
    }

    public void setGruz(int gruzopodemnost) {
        this.gruzopodemnost = gruzopodemnost;
    }

    public void setSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void goodok() {
        System.out.println("Too-too!!!");
    }

    public static String show_description() {
        return "Это класс для камаз!";
    }

    @Override
    public String toString() {
        return "Kamaz (" + gruzopodemnost + ", " + maxSpeed + ", " + color + ")";
    }
    public static void main(String[] args) {
        Kamaz kamaz1 = new Kamaz(10000, 80);
        System.out.println(kamaz1);
        kamaz1.goodok();

        kamaz1.setColor("красный");
        kamaz1.setGruz(12000);
        kamaz1.setSpeed(90);

        System.out.println("Цвет: " + kamaz1.getColor());
        System.out.println("Грузоподъёмность: " + kamaz1.getGruz());
        System.out.println("Скорость: " + kamaz1.getSpeed());

        System.out.println(Kamaz.show_description());
    }
}