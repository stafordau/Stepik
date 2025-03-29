package task3_5_5;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

class Registration {
    public static void main(String[] args) {
        Map<String, Dimension> resolutions = new HashMap<>();
        String[] options = {
                "800x600", "1024x768", "1200x600",
                "1280x1024", "1680x1050", "1920x1080"
        };

        JPanel panel = new JPanel(new GridLayout(0, 1));
        ButtonGroup group = new ButtonGroup();
        JRadioButton[] buttons = new JRadioButton[options.length];

        for (int i = 0; i < options.length; i++) {
            String res = options[i];
            buttons[i] = new JRadioButton(res);
            group.add(buttons[i]);
            panel.add(buttons[i]);

            String[] parts = res.split("x");
            resolutions.put(res, new Dimension(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        }

        buttons[2].setSelected(true);

        int resResult = JOptionPane.showConfirmDialog(null, panel, "Выберите разрешение", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resResult != JOptionPane.OK_OPTION) {
            System.exit(0);
        }

        Dimension selectedSize = null;
        for (JRadioButton btn : buttons) {
            if (btn.isSelected()) {
                selectedSize = resolutions.get(btn.getText());
                break;
            }
        }

        JFrame frame = new JFrame("Регистрация");
        frame.setSize(selectedSize);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        int start = JOptionPane.showConfirmDialog(
                frame,
                "Добро пожаловать!\nПройдете регистрацию?",
                "Регистрация",
                JOptionPane.YES_NO_OPTION
        );

        if (start != JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        String login;
        while (true) {
            login = JOptionPane.showInputDialog(frame, "Введите логин (более 5 символов, без пробелов):");
            if (login == null) System.exit(0);
            if (login.length() > 5 && !login.contains(" ")) break;
        }

        String password;
        while (true) {
            JPasswordField passField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    frame, passField, "Введите пароль (более 8 символов, буквы+цифры):", JOptionPane.OK_CANCEL_OPTION
            );
            if (option != JOptionPane.OK_OPTION) System.exit(0);
            password = new String(passField.getPassword());
            if (isValidPassword(password)) break;
        }

        while (true) {
            JPasswordField repeatField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    frame, repeatField, "Повторите пароль:", JOptionPane.OK_CANCEL_OPTION
            );
            if (option != JOptionPane.OK_OPTION) System.exit(0);
            String repeatPassword = new String(repeatField.getPassword());
            if (repeatPassword.equals(password)) break;
        }

        JOptionPane.showMessageDialog(frame, "Добро пожаловать, " + login + "!", "Успешная регистрация", JOptionPane.INFORMATION_MESSAGE);
    }

    private static boolean isValidPassword(String password) {
        if (password.length() <= 8 || password.contains(" ")) return false;

        boolean hasLetter = false;
        boolean hasDigit = false;

        for (char ch : password.toCharArray()) {
            if (Character.isLetter(ch)) hasLetter = true;
            if (Character.isDigit(ch)) hasDigit = true;
        }

        return hasLetter && hasDigit;
    }
}