package gui3_7_2;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import net.miginfocom.swing.*;

public class TextEditor extends JFrame {
    public TextEditor() {
        initComponents();
    }

    private File currentFile = null;

    private void initComponents() {
        openButton = new JButton();
        label1 = new JLabel();
        saveButton = new JButton();
        saveAsButton = new JButton();
        scrollPane4 = new JScrollPane();
        textArea1 = new JTextArea();

        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "fillx, filly, hidemode 3",
                "[fill][fill][grow][fill][fill]",
                "[] [grow]"));

        openButton.setText("Открыть файл");
        contentPane.add(openButton, "cell 0 0");

        label1.setText("Открыт файл: -");
        contentPane.add(label1, "cell 1 0 2 1");

        saveButton.setText("Сохранить");
        contentPane.add(saveButton, "cell 3 0");

        saveAsButton.setText("Сохранить как");
        contentPane.add(saveAsButton, "cell 4 0");

        scrollPane4.setViewportView(textArea1);
        contentPane.add(scrollPane4, "cell 0 1 5 1, grow");

        pack();
        setLocationRelativeTo(getOwner());

        openButton.addActionListener(e -> openFile());
        saveButton.addActionListener(e -> saveFile(false));
        saveAsButton.addActionListener(e -> saveFile(true));
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(currentFile))) {
                textArea1.setText("");
                String line;
                while ((line = reader.readLine()) != null) {
                    textArea1.append(line + "\n");
                }
                label1.setText("Открыт файл: " + currentFile.getName());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка при открытии файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile(boolean saveAs) {
        if (saveAs || currentFile == null) {
            JFileChooser chooser = new JFileChooser();
            int result = chooser.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                currentFile = chooser.getSelectedFile();
            } else {
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(currentFile))) {
            writer.write(textArea1.getText());
            label1.setText("Открыт файл: " + currentFile.getName());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Ошибка при сохранении файла", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private JButton openButton;
    private JLabel label1;
    private JButton saveButton;
    private JButton saveAsButton;
    private JScrollPane scrollPane4;
    private JTextArea textArea1;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TextEditor editor = new TextEditor();
            editor.setTitle("Текстовый редактор");
            editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editor.setSize(800, 600);
            editor.setLocationRelativeTo(null);
            editor.setVisible(true);
        });
    }
}
