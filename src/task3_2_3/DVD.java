package task3_2_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class DVD extends JPanel implements ActionListener {
    private int x = 100, y = 100;
    private int dx = 4, dy = 4;
    private Color logoColor;
    private Timer timer;
    private final Random random = new Random();
    private final Font font = new Font("Arial", Font.BOLD, 60);
    private int textWidth, textHeight;

    public DVD() {
        setBackground(Color.BLACK);
        logoColor = getRandomColor();
        timer = new Timer(10, this);
        timer.start();
    }

    private Color getRandomColor() {
        return new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(font);
        FontMetrics fm = g.getFontMetrics();
        String text = "DVD";
        textWidth = fm.stringWidth(text);
        textHeight = fm.getAscent();

        g.setColor(logoColor);
        g.drawString(text, x, y + textHeight);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x += dx;
        y += dy;

        boolean bounced = false;

        if (x <= 0 || x + textWidth >= getWidth()) {
            dx *= -1;
            bounced = true;
        }
        if (y <= 0 || y + textHeight >= getHeight()) {
            dy *= -1;
            bounced = true;
        }

        if (bounced) {
            logoColor = getRandomColor();
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DVD Logo Animation");
        DVD animationPanel = new DVD();
        frame.add(animationPanel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}