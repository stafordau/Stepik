package task3_3_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Controlled extends JPanel {
    private BufferedImage objectImage;
    private int objX = 100, objY = 100;
    private final int objSize = 50;
    private final int step = 50;

    public Controlled() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setFocusable(true);
        requestFocusInWindow();

        try {
            BufferedImage original = ImageIO.read(new File("src/task3_2_6/car4.png"));
            objectImage = new BufferedImage(objSize, objSize, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = objectImage.createGraphics();
            g2d.drawImage(original, 0, 0, objSize, objSize, null);
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int panelWidth = getWidth();
                int panelHeight = getHeight();

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        if (objX - step >= 0) objX -= step;
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (objX + step + objSize <= panelWidth) objX += step;
                        break;
                    case KeyEvent.VK_UP:
                        if (objY - step >= 0) objY -= step;
                        break;
                    case KeyEvent.VK_DOWN:
                        if (objY + step + objSize <= panelHeight) objY += step;
                        break;
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (objectImage != null) {
            g.drawImage(objectImage, objX, objY, null);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Move Object with Arrow Keys");
        Controlled panel = new Controlled();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}