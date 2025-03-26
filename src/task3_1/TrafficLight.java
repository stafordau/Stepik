package task3_1;

import javax.swing.*;
import java.awt.*;

public class TrafficLight extends JPanel {
    private static final int WIDTH = 200;
    private static final int HEIGHT = 400;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //корпус
        g.setColor(Color.BLACK);
        g.fillRoundRect(50, 50, 100, 300, 20, 20);

        //красный
        g.setColor(Color.RED);
        g.fillOval(75, 70, 50, 50);

        //желтый
        g.setColor(Color.YELLOW);
        g.fillOval(75, 160, 50, 50);

        //зеленый
        g.setColor(Color.GREEN);
        g.fillOval(75, 250, 50, 50);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light");
        TrafficLight light = new TrafficLight();
        frame.add(light);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}