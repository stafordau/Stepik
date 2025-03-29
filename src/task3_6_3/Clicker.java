package task3_6_3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Clicker {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Кликер");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = new JLabel("Кликов: 0");
        label.setFont(new Font("Arial", Font.BOLD, 26));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Клик");
        button.setFont(new Font("Arial", Font.PLAIN, 18));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            int count = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                label.setText("Кликов: " + count);
            }
        });

        panel.add(Box.createVerticalGlue());
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));
        panel.add(button);
        panel.add(Box.createVerticalGlue());

        frame.add(panel);
        frame.setVisible(true);
    }
}