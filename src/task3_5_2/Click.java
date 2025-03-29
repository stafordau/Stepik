package task3_5_2;

import javax.swing.*;

class Click {
    public static void main(String[] args) {
        String name = JOptionPane.showInputDialog(null, "Введите ваше имя:", "Ввод имени", JOptionPane.QUESTION_MESSAGE);

        JOptionPane.showMessageDialog(null, "Ваше имя: " + name, "Информация", JOptionPane.INFORMATION_MESSAGE);
    }
}