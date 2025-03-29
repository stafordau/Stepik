
package task3_4_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

class Click extends JPanel {
    private final List<Point> clickPoints = new ArrayList<>();
    private final int radius = 10;
    private Point draggedPoint = null;
    private Point dragOffset = null;

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
                    Point toRemove = findPointNear(clicked);
                    if (toRemove != null) {
                        clickPoints.remove(toRemove);
                        repaint();
                    }
                } else if (SwingUtilities.isRightMouseButton(e)) {
                    draggedPoint = findPointNear(clicked);
                    if (draggedPoint != null) {
                        dragOffset = new Point(clicked.x - draggedPoint.x, clicked.y - draggedPoint.y);
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    draggedPoint = null;
                    dragOffset = null;
                }
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (draggedPoint != null && SwingUtilities.isRightMouseButton(e)) {
                    draggedPoint.setLocation(e.getX() - dragOffset.x, e.getY() - dragOffset.y);
                    repaint();
                }
            }
        });
    }

    private Point findPointNear(Point target) {
        for (Point p : clickPoints) {
            if (target.distance(p) <= radius) {
                return p;
            }
        }
        return null;
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
