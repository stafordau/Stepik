package task3_1;

import javax.swing.*;
import java.awt.*;

public class Snowman extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //фон
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, getWidth(), getHeight());

        //тело
        g.setColor(Color.WHITE);
        g.fillOval(100, 200, 200, 200); //низ
        g.fillOval(125, 120, 150, 150); //середина
        g.fillOval(150, 50, 100, 100);  //голова

        //шляпа
        g.setColor(Color.BLACK);
        g.fillRect(160, 20, 80, 30);  //основа
        g.fillRect(140, 50, 120, 10); //козырек

        //глаза
        g.setColor(Color.BLACK);
        g.fillOval(175, 80, 10, 10); //левый
        g.fillOval(215, 80, 10, 10); //правый

        //рот
        for (int i = 0; i < 5; i++) {
            g.fillOval(180 + i * 10, 105, 5, 5);
        }

        //нос
        g.setColor(Color.ORANGE);
        int[] xPoints = {195, 230, 195};
        int[] yPoints = {95, 100, 105};
        g.fillPolygon(xPoints, yPoints, 3);

        //пуговицы
        g.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            g.fillOval(195, 160 + i * 40, 10, 10);
        }

        //руки
        g.setColor(new Color(139, 69, 19)); //ветки
        g.drawLine(125, 150, 50, 100); //левая
        g.drawLine(275, 150, 350, 100); //правая
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Snowman");
        Snowman snowman = new Snowman();
        frame.add(snowman);
        frame.setSize(400, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
