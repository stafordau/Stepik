package task3_6_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Spinner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Книги и Цвет");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel booksLabel = new JLabel("Сколько книг вы возьмете?");
        booksLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(booksLabel);

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 0, 100, 1));
        spinner.setMaximumSize(new Dimension(100, 30));
        spinner.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(spinner);

        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        JLabel colorLabel = new JLabel("Выберите любимый цвет:");
        colorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(colorLabel);

        String[] colors = {"Красный", "Синий", "Зелёный", "Жёлтый"};
        JComboBox<String> colorBox = new JComboBox<>(colors);
        colorBox.setMaximumSize(new Dimension(200, 30));
        colorBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(colorBox);

        JCheckBox customCheck = new JCheckBox("Свой вариант");
        customCheck.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(customCheck);

        JTextField customField = new JTextField();
        customField.setMaximumSize(new Dimension(200, 30));
        customField.setEnabled(false);
        customField.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(customField);

        customCheck.addActionListener(e -> customField.setEnabled(customCheck.isSelected()));

        frame.add(Box.createRigidArea(new Dimension(0, 15)));
        JButton button = new JButton("Ответить");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(button);

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel answerLabel = new JLabel("Ответ: ");
        answerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(answerLabel);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int bookCount = (int) spinner.getValue();
                String color;

                if (customCheck.isSelected()) {
                    color = customField.getText().trim();
                    if (color.isEmpty()) {
                        color = "(не указан)";
                    }
                } else {
                    color = (String) colorBox.getSelectedItem();
                }

                answerLabel.setText("Ответ: " + bookCount + " книг, цвет: " + color);
            }
        });

        frame.setVisible(true);
    }
}