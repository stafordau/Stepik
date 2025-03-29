package task3_7_1;

import javax.swing.*;
import java.awt.*;

class Form {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Форма регистрации");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 700);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        int fieldWidth = 300;
        int fieldHeight = 25;

        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField emailField = new JTextField();
        emailField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        emailField.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField phoneField = new JTextField();
        phoneField.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        phoneField.setAlignmentX(Component.CENTER_ALIGNMENT);

        String[] genders = {"Мужской", "Женский"};
        JComboBox<String> genderBox = new JComboBox<>(genders);
        genderBox.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        genderBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox foodCheck = new JCheckBox("Нужен обед");
        foodCheck.setAlignmentX(Component.CENTER_ALIGNMENT);

        JCheckBox transportCheck = new JCheckBox("Нужен трансфер");
        transportCheck.setAlignmentX(Component.CENTER_ALIGNMENT);

        JRadioButton offline = new JRadioButton("Офлайн", true);
        offline.setAlignmentX(Component.CENTER_ALIGNMENT);

        ButtonGroup formatGroup = new ButtonGroup();
        formatGroup.add(offline);

        String[] countries = {"Россия", "Беларусь", "Другое"};
        JComboBox<String> countryBox = new JComboBox<>(countries);
        countryBox.setMaximumSize(new Dimension(fieldWidth, fieldHeight));
        countryBox.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSpinner ageSpinner = new JSpinner(new SpinnerNumberModel(18, 14, 100, 1));
        ageSpinner.setMaximumSize(new Dimension(80, fieldHeight));
        ageSpinner.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea commentArea = new JTextArea(5, 20);
        JScrollPane commentScroll = new JScrollPane(commentArea);
        commentScroll.setMaximumSize(new Dimension(fieldWidth, 100));
        commentScroll.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton submitButton = new JButton("Отправить");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel fioLabel = new JLabel("ФИО:");
        fioLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(fioLabel);
        frame.add(nameField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(emailLabel);
        frame.add(emailField);

        JLabel phoneLabel = new JLabel("Телефон:");
        phoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(phoneLabel);
        frame.add(phoneField);

        JLabel genderLabel = new JLabel("Пол:");
        genderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(genderLabel);
        frame.add(genderBox);

        JLabel ageLabel = new JLabel("Возраст:");
        ageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(ageLabel);
        frame.add(ageSpinner);

        JLabel formatLabel = new JLabel("Формат участия:");
        formatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(formatLabel);
        frame.add(offline);

        frame.add(foodCheck);
        frame.add(transportCheck);

        JLabel countryLabel = new JLabel("Страна:");
        countryLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(countryLabel);
        frame.add(countryBox);

        JLabel commentLabel = new JLabel("Комментарий:");
        commentLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        frame.add(commentLabel);
        frame.add(commentScroll);

        frame.add(Box.createRigidArea(new Dimension(0, 10)));
        frame.add(submitButton);

        submitButton.addActionListener(e -> {
            String info = "ФИО: " + nameField.getText() + "\n"
                    + "Email: " + emailField.getText() + "\n"
                    + "Телефон: " + phoneField.getText() + "\n"
                    + "Пол: " + genderBox.getSelectedItem() + "\n"
                    + "Возраст: " + ageSpinner.getValue() + "\n"
                    + "Формат: " + "Офлайн" + "\n"
                    + "Обед: " + (foodCheck.isSelected() ? "Да" : "Нет") + "\n"
                    + "Трансфер: " + (transportCheck.isSelected() ? "Да" : "Нет") + "\n"
                    + "Страна: " + countryBox.getSelectedItem() + "\n"
                    + "Комментарий: " + commentArea.getText();

            JOptionPane.showMessageDialog(frame, info, "Введённые данные", JOptionPane.INFORMATION_MESSAGE);
        });

        frame.setVisible(true);
    }
}
