package task3_6_4;

import javax.swing.*;
import java.awt.*;

class Spinner {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Книги, Цвет и Время года");
        frame.setSize(500, 700);
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

        frame.add(Box.createRigidArea(new Dimension(0, 15)));
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
        JLabel seasonLabel = new JLabel("Выберите любимое время года:");
        seasonLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(seasonLabel);

        JRadioButton spring = new JRadioButton("Весна", true);
        JRadioButton summer = new JRadioButton("Лето");
        JRadioButton autumn = new JRadioButton("Осень");
        JRadioButton winter = new JRadioButton("Зима");

        ButtonGroup seasonGroup = new ButtonGroup();
        seasonGroup.add(spring);
        seasonGroup.add(summer);
        seasonGroup.add(autumn);
        seasonGroup.add(winter);

        JPanel seasonPanel = new JPanel();
        seasonPanel.setLayout(new BoxLayout(seasonPanel, BoxLayout.Y_AXIS));
        seasonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        seasonPanel.add(spring);
        seasonPanel.add(summer);
        seasonPanel.add(autumn);
        seasonPanel.add(winter);

        frame.add(seasonPanel);

        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        JButton button = new JButton("Ответить");
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(button);

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        JLabel answerLabel = new JLabel("Ответ: ");
        answerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        answerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(answerLabel);

        frame.add(Box.createRigidArea(new Dimension(0, 20)));
        JTextField inputField = new JTextField();
        inputField.setMaximumSize(new Dimension(300, 30));
        inputField.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(inputField);

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        JButton writeButton = new JButton("Записать");
        writeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(writeButton);

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 150));
        scrollPane.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(scrollPane);

        button.addActionListener(e -> {
            int books = (int) spinner.getValue();
            String color;

            if (customCheck.isSelected()) {
                color = customField.getText().trim();
                if (color.isEmpty()) color = "(не указан)";
            } else {
                color = (String) colorBox.getSelectedItem();
            }

            String season = "";
            if (spring.isSelected()) season = "Весна";
            else if (summer.isSelected()) season = "Лето";
            else if (autumn.isSelected()) season = "Осень";
            else if (winter.isSelected()) season = "Зима";

            answerLabel.setText("Ответ: " + books + " книг, цвет: " + color + ", сезон: " + season);
        });

        writeButton.addActionListener(e -> {
            String input = inputField.getText().trim();
            if (!input.isEmpty()) {
                textArea.append(input + "\n");
                inputField.setText("");
            }
        });

        frame.setVisible(true);
    }
}