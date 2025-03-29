package task3_2_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

class Earth extends JPanel implements ActionListener {
    private double angle = 0;
    private final Timer timer;
    private final int radius = 150;
    private BufferedImage earthImg, sunImg;
    private final int centerX, centerY;

    public Earth(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);

        centerX = width / 2;
        centerY = height / 2;

        try {
            earthImg = ImageIO.read(new File("src/task3_2_4/earth.png"));
            sunImg = ImageIO.read(new File("src/task3_2_4/sun.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int sunSize = 150;
        int earthSize = 100;

        if (sunImg != null) {
            g2d.drawImage(sunImg, centerX - sunSize / 2, centerY - sunSize / 2, sunSize, sunSize, null);
        }

        int x = (int) (centerX + radius * Math.cos(angle));
        int y = (int) (centerY + radius * Math.sin(angle));

        if (earthImg != null) {
            g2d.drawImage(earthImg, x - earthSize / 2, y - earthSize / 2, earthSize, earthSize, null);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        angle += 0.02;
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Eart");
        Earth panel = new Earth(800, 600);
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}