package task3_4_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Click extends JPanel {
    private final List<Point> clickPoints = new ArrayList<>();
    private final int radius = 10; // радиус кружка

    public Click() {
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);

        Cursor customCursor = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
        setCursor(customCursor);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                Point clicked = e.getPoint();

                if (SwingUtilities.isLeftMouseButton(e)) {
                    clickPoints.add(clicked);
                    repaint();
                } else if (SwingUtilities.isMiddleMouseButton(e)) {
                    Point toRemove = null;
                    for (Point p : clickPoints) {
                        if (clicked.distance(p) <= radius) {
                            toRemove = p;
                            break;
                        }
                    }
                    if (toRemove != null) {
                        clickPoints.remove(toRemove);
                        repaint();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.GREEN);
        for (Point p : clickPoints) {
            g.fillOval(p.x - radius, p.y - radius, radius * 2, radius * 2);
            g.setColor(Color.BLACK);
            g.drawString("(" + p.x + ", " + p.y + ")", p.x + radius + 4, p.y);
            g.setColor(Color.GREEN);
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