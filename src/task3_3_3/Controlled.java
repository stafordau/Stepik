package task3_3_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Controlled extends JPanel implements KeyListener, ActionListener {
    private BufferedImage image;
    private int x = 100, y = 100;
    private final int step = 5;
    private final int objectSize = 50;

    private final Set<Integer> keysPressed = new HashSet<>();
    private final Timer timer;

    public Controlled() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
        setFocusable(true);
        addKeyListener(this);

        try {
            image = ImageIO.read(new File("src/task3_2_6/car4.png"));
        } catch (IOException e) {
            System.out.println("Не удалось загрузить изображение.");
        }

        timer = new Timer(20, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (image != null) {
            g.drawImage(image, x, y, objectSize, objectSize, null);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keysPressed.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keysPressed.remove(e.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        if (keysPressed.contains(KeyEvent.VK_LEFT)) {
            x -= step;
        }
        if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
            x += step;
        }
        if (keysPressed.contains(KeyEvent.VK_UP)) {
            y -= step;
        }
        if (keysPressed.contains(KeyEvent.VK_DOWN)) {
            y += step;
        }

        // Телепортация
        if (x < -objectSize) x = panelWidth;
        if (x > panelWidth) x = -objectSize;
        if (y < -objectSize) y = panelHeight;
        if (y > panelHeight) y = -objectSize;

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Wraparound Movement");
        Controlled panel = new Controlled();
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}