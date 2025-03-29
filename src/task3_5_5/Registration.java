package task3_5_5;

import javax.swing.*;

class Registration {
    public static void main(String[] args) {
        int start = JOptionPane.showConfirmDialog(
                null,
                "Добро пожаловать!\nПройдете регистрацию?",
                "Регистрация",
                JOptionPane.YES_NO_OPTION
        );

        if (start != JOptionPane.YES_OPTION) {
            System.exit(0);
        }

        String login;
        while (true) {
            login = JOptionPane.showInputDialog(null, "Введите логин (более 5 символов, без пробелов):", "Логин", JOptionPane.QUESTION_MESSAGE);
            if (login != null && login.length() > 5 && !login.contains(" ")) {
                break;
            }
        }

        String password;
        while (true) {
            JPasswordField passField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    null, passField, "Введите пароль:", JOptionPane.OK_CANCEL_OPTION
            );
            if (option == JOptionPane.OK_OPTION) {
                password = new String(passField.getPassword());
                if (isValidPassword(password)) {
                    break;
                }
            }
        }

        while (true) {
            JPasswordField repeatField = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    null, repeatField, "Повторите пароль:", JOptionPane.OK_CANCEL_OPTION
            );
            if (option == JOptionPane.OK_OPTION) {
                String repeatPassword = new String(repeatField.getPassword());
                if (repeatPassword.equals(password)) {
                    break;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "Добро пожаловать, " + login + "!", "Успех", JOptionPane.INFORMATION_MESSAGE);
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