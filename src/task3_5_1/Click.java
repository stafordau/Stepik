package task3_5_1;

import javax.swing.*;
import java.awt.event.*;

class Click {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Нажми пробел");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);

        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    JOptionPane.showMessageDialog(frame, "Юлия", "Информация", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}