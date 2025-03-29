package task3_6_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Spinner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("JSpinner");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(Box.createRigidArea(new Dimension(0, 20)));

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        spinner.setMaximumSize(new Dimension(100, 30));
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(spinner);

        frame.add(Box.createRigidArea(new Dimension(0, 15)));

        JButton button = new JButton("Ответить");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(button);

        frame.add(Box.createRigidArea(new Dimension(0, 15)));

        JLabel answerLabel = new JLabel("Ответ: ");
        answerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(answerLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int value = (int) spinner.getValue();
                answerLabel.setText("Ответ: " + value);
            }
        });

        frame.setVisible(true);
    }
}