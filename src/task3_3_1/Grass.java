package task3_3_1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

class Grass extends JPanel {
    private BufferedImage grassTexture;

    public Grass() {
        setPreferredSize(new Dimension(800, 600));

        try {
            BufferedImage original = ImageIO.read(new File("src/task3_3_1/grass.png"));
            grassTexture = new BufferedImage(50, 50, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2d = grassTexture.createGraphics();
            g2d.drawImage(original, 0, 0, 50, 50, null);
            g2d.dispose();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (grassTexture != null) {
            for (int y = 0; y < getHeight(); y += 50) {
                for (int x = 0; x < getWidth(); x += 50) {
                    g.drawImage(grassTexture, x, y, null);
                }
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Grass Texture Fill");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Grass());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}