package task3_2_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Img extends JPanel implements ActionListener {
    private Image image;
    private int x = 0, y = 0;
    private final int panelSize = 400;
    private final int speed = 2;
    private Timer timer;

    public Img() {
        setPreferredSize(new Dimension(panelSize, panelSize));
        image = new ImageIcon("src/task3_2_1/Sun.png").getImage();
        timer = new Timer(10, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, x, y, 100, 100, this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += speed;
        y += speed;

        // Если ушло за границы панели — сброс
        if (x > panelSize || y > panelSize) {
            x = 0;
            y = 0;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Прямая анимация изображения");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Img());
        frame.pack();
        frame.setVisible(true);
    }
}