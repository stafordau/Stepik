package task3_4_1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Click extends JPanel {
    private int clickX = -1;
    private int clickY = -1;

    public Click() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    clickX = e.getX();
                    clickY = e.getY();
                    repaint();
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (clickX >= 0 && clickY >= 0) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 14));
            g.drawString("(" + clickX + ", " + clickY + ")", clickX + 10, clickY - 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Координаты");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Click());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}