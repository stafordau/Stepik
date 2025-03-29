package task3_5_3;

import javax.swing.*;

public class Questions {
    public static void main(String[] args) {
        int answer1 = JOptionPane.showConfirmDialog(null, "Вам нравятся детективы?", "Вопрос 1", JOptionPane.YES_NO_OPTION);
        int answer2 = JOptionPane.showConfirmDialog(null, "Вам нравится фэнтези?", "Вопрос 2", JOptionPane.YES_NO_OPTION);

        String message = "";

        if (answer1 == JOptionPane.YES_OPTION && answer2 == JOptionPane.YES_OPTION) {
            message = "Загадки и волшебство отличный выбор";
        } else if (answer1 == JOptionPane.YES_OPTION && answer2 == JOptionPane.NO_OPTION) {
            message = "Любитель тайн!";
        } else if (answer1 == JOptionPane.NO_OPTION && answer2 == JOptionPane.YES_OPTION) {
            message = "Любитель магии!";
        } else if (answer1 == JOptionPane.NO_OPTION && answer2 == JOptionPane.NO_OPTION) {
            message = "По душе романы!";
        }

        JOptionPane.showMessageDialog(null, message, "Результат", JOptionPane.INFORMATION_MESSAGE);
    }
}