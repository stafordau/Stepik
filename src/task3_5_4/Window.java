package task3_5_4;

import javax.swing.*;

class Window {
    public static void main(String[] args) {
        String[] resolutions = {
                "800 x 600",
                "1024 x 768",
                "1280 x 720",
                "1920 x 1080"
        };

        JComboBox<String> comboBox = new JComboBox<>(resolutions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Выберите разрешение экрана:"));
        panel.add(comboBox);

        int result = JOptionPane.showConfirmDialog(null, panel, "Настройки экрана",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            String selected = (String) comboBox.getSelectedItem();
            String[] parts = selected.split(" x ");
            int width = Integer.parseInt(parts[0].trim());
            int height = Integer.parseInt(parts[1].trim());

            JFrame frame = new JFrame("Окно " + selected);
            frame.setSize(width, height);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }
    }
}