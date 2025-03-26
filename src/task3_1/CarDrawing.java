package task3_1;

import javax.swing.*;
import java.awt.*;

public class CarDrawing extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        // Цвет машины
        Color carColor = new Color(240, 230, 100); // Желтый

        // Корпус машины
        g2.setColor(carColor);
        g2.fillRoundRect(100, 150, 300, 100, 100, 100); // Нижняя часть
        g2.fillArc(100, 100, 200, 100, 0, 180); // Верхняя часть

        // Окна
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(140, 130, 60, 40, 20, 20);
        g2.fillRoundRect(220, 130, 60, 40, 20, 20);
        g2.setColor(Color.BLACK);
        g2.drawLine(200, 130, 200, 170); // Разделитель окон

        // Фары
        g2.setColor(Color.WHITE);
        g2.fillOval(380, 170, 20, 10);

        // Дверь
        g2.setColor(Color.BLACK);
        g2.drawRect(210, 150, 90, 70);
        g2.drawLine(300, 150, 300, 220);
        g2.fillRect(285, 190, 10, 5); // Ручка двери

        // Колеса
        g2.setColor(Color.BLACK);
        g2.fillOval(130, 220, 50, 50);
        g2.fillOval(290, 220, 50, 50);
        g2.setColor(Color.WHITE);
        g2.fillOval(145, 235, 20, 20);
        g2.fillOval(305, 235, 20, 20);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Drawing");
        CarDrawing panel = new CarDrawing();
        frame.add(panel);
        frame.setSize(500, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}