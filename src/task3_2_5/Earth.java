package task3_2_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;

class Earth extends JPanel implements ActionListener {
    private BufferedImage sunImg;
    private final java.util.List<Planet> planets = new ArrayList<>();
    private final javax.swing.Timer timer;
    private final int centerX, centerY;

    public Earth(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        centerX = width / 2;
        centerY = height / 2;

        try {
            sunImg = ImageIO.read(new File("src/task3_2_4/sun.png"));

            // Растояние от Солнца, скорость и размеры не в 100% масштабе, но пропорциональны
            planets.add(new Planet("src/task3_2_5/mercury.png", 50, 0.05, 10));   // Меркурий
            planets.add(new Planet("src/task3_2_5/venus.png",   80, 0.035, 16));  // Венера
            planets.add(new Planet("src/task3_2_5/earth.png",  110, 0.03, 18));   // Земля
            planets.add(new Planet("src/task3_2_5/mars.png",   140, 0.024, 16));  // Марс
            planets.add(new Planet("src/task3_2_5/jupiter.png",200, 0.013, 28));  // Юпитер
            planets.add(new Planet("src/task3_2_5/saturn.png", 260, 0.01, 26));   // Сатурн
            planets.add(new Planet("src/task3_2_5/uranus.png", 310, 0.007, 22));  // Уран
            planets.add(new Planet("src/task3_2_5/neptune.png",360, 0.005, 22));  // Нептун
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new javax.swing.Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Солнце
        if (sunImg != null) {
            int sunSize = 80;
            g2d.drawImage(sunImg, centerX - sunSize / 2, centerY - sunSize / 2, sunSize, sunSize, null);
        }

        // Орбиты (необязательно, но красиво)
        g2d.setColor(Color.DARK_GRAY);
        for (Planet p : planets) {
            g2d.drawOval(centerX - p.radius, centerY - p.radius, p.radius * 2, p.radius * 2);
        }

        // Планеты
        for (Planet p : planets) {
            int x = (int) (centerX + p.radius * Math.cos(p.angle));
            int y = (int) (centerY + p.radius * Math.sin(p.angle));
            if (p.image != null) {
                g2d.drawImage(p.image, x - p.size / 2, y - p.size / 2, p.size, p.size, null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Planet p : planets) {
            p.angle += p.speed;
        }
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Realistic Solar System");
        Earth panel = new Earth(1000, 800);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static class Planet {
        BufferedImage image;
        int radius;
        double angle = 0;
        double speed;
        int size;

        Planet(String path, int radius, double speed, int size) throws IOException {
            image = ImageIO.read(new File(path));
            this.radius = radius;
            this.speed = speed;
            this.size = size;
        }
    }
}