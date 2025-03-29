package task3_6_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Calculator {
    private static JTextField display;
    private static String current = "";
    private static double firstOperand = 0;
    private static String operator = "";

    public static void main(String[] args) {
        JFrame frame = new JFrame("Калькулятор");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        frame.add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        String[] buttons = {
                "1", "2", "3", "+",
                "4", "5", "6", "-",
                "7", "8", "9", "*",
                "C", "0", "=", "/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                current += command;
                display.setText(current);
            } else if (command.matches("[+\\-*/]")) {
                if (!current.isEmpty()) {
                    firstOperand = Double.parseDouble(current);
                    operator = command;
                    current = "";
                    display.setText("");
                }
            } else if (command.equals("=")) {
                if (!current.isEmpty() && !operator.isEmpty()) {
                    double secondOperand = Double.parseDouble(current);
                    double result = 0;
                    boolean valid = true;

                    switch (operator) {
                        case "+": result = firstOperand + secondOperand; break;
                        case "-": result = firstOperand - secondOperand; break;
                        case "*": result = firstOperand * secondOperand; break;
                        case "/":
                            if (secondOperand != 0) {
                                result = firstOperand / secondOperand;
                            } else {
                                display.setText("Ошибка: деление на 0");
                                valid = false;
                            }
                            break;
                    }

                    if (valid) {
                        display.setText(String.valueOf(result));
                        current = String.valueOf(result);
                        operator = "";
                    } else {
                        current = "";
                        operator = "";
                    }
                }
            } else if (command.equals("C")) {
                current = "";
                operator = "";
                display.setText("");
            }
        }
    }
}