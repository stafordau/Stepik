package task3_2_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Img extends JPanel implements ActionListener {
    private final Image image;
    private int x = 0, y = 0;
    private final int panelSize = 400;
    private final int speed = 2;
    private final Timer timer;
    private int direction = 0;

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
        switch (direction) {
            case 0:
                x += speed;
                if (x >= panelSize - 100) direction = 1;
                break;
            case 1:
                y += speed;
                if (y >= panelSize - 60) direction = 2;
                break;
            case 2:
                x -= speed;
                if (x <= 0) direction = 3;
                break;
            case 3:
                y -= speed;
                if (y <= 0) direction = 0;
                break;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Движение по квадрату");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Img());
        frame.pack();
        frame.setVisible(true);
    }
}