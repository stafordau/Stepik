package task3_6_1;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class Widgets {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Окно");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel label = new JLabel("Умничка", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.ITALIC, 50));

        JSlider slider = new JSlider(JSlider.HORIZONTAL, 5, 100, 50);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int fontSize = slider.getValue();
                label.setFont(new Font("Serif", Font.ITALIC, fontSize));
            }
        });

        frame.add(label, BorderLayout.CENTER);
        frame.add(slider, BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}