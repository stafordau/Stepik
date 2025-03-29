package task3_6_1;

import javax.swing.*;
import java.awt.*;

class Widgets {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Окно");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Умничка", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.ITALIC, 50));

        frame.add(label, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}