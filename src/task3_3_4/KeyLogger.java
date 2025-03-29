package task3_3_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class KeyLogger extends JFrame {
    private final JTextArea textArea;
    private final JButton saveButton;

    public KeyLogger()  {
        setTitle("Text Capture");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);
        saveButton = new JButton("Сохранить в файл");

        saveButton.addActionListener(e -> {
            try {
                saveTextToFile();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при сохранении: " + ex.getMessage());
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void saveTextToFile() throws IOException {
        String text = textArea.getText();
        BufferedWriter writer = new BufferedWriter(new FileWriter("input.txt"));
        writer.write(text);
        writer.close();
        JOptionPane.showMessageDialog(this, "Текст сохранён в input.txt");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new KeyLogger().setVisible(true);
        });
    }
}