package task3_2_6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

class CarRace extends JPanel implements ActionListener {
    private final int[] carX = new int[5];
    private final int carYSpacing = 80;
    private final int carWidth = 120;
    private final int carHeight = 60;
    private boolean raceStarted = false;
    private boolean raceOver = false;
    private int winner = -1;

    private final Random rand = new Random();
    private final javax.swing.Timer timer;

    private final BufferedImage[] carImages = new BufferedImage[5];

    public CarRace() {
        setPreferredSize(new Dimension(900, 550));
        setBackground(Color.WHITE);

        for (int i = 0; i < carX.length; i++) {
            carX[i] = 10;
        }

        try {
            for (int i = 0; i < 5; i++) {
                carImages[i] = ImageIO.read(new File("src/task3_2_6/car" + (i + 1) + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        new javax.swing.Timer(3000, e -> raceStarted = true).start();

        timer = new javax.swing.Timer(30, this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int finishX = getWidth() - 100;
        g.setColor(Color.RED);
        g.drawLine(finishX, 0, finishX, getHeight());

        for (int i = 0; i < carX.length; i++) {
            int y = 60 + i * carYSpacing;

            if (carImages[i] != null) {
                g.drawImage(carImages[i], carX[i], y, carWidth, carHeight, null);
            } else {
                g.setColor(Color.GRAY);
                g.fillRect(carX[i], y, carWidth, carHeight);
            }
        }

        if (raceOver && winner != -1) {
            g.setFont(new Font("Arial", Font.BOLD, 36));
            g.setColor(Color.BLACK);
            String msg = "Победитель: Машина " + (winner + 1);
            int textWidth = g.getFontMetrics().stringWidth(msg);
            g.drawString(msg, (getWidth() - textWidth) / 2, getHeight() / 2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (raceStarted && !raceOver) {
            int finishX = getWidth() - 100;

            for (int i = 0; i < carX.length; i++) {
                carX[i] += rand.nextInt(5) + 1;

                if (carX[i] + carWidth >= finishX) {
                    winner = i;
                    raceOver = true;
                    break;
                }
            }
        }

        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Car Race (with images)");
        CarRace race = new CarRace();
        frame.add(race);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}