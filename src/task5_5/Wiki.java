package task5_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.JSONObject;

public class Wiki {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Wiki::createGUI);
    }

    private static void createGUI() {
        JFrame frame = new JFrame("Поиск в Википедии");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLayout(new BorderLayout());

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Поиск");

        JTextArea resultArea = new JTextArea();
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        searchButton.addActionListener((ActionEvent e) -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                String result = fetchWikipediaSummary(query);
                resultArea.setText(result);
            }
        });

        frame.setVisible(true);
    }

    private static String fetchWikipediaSummary(String query) {
        try {
            String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
            String apiUrl = "https://ru.wikipedia.org/api/rest_v1/page/summary/" + encodedQuery;

            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Java Wikipedia Client");

            Scanner scanner = new Scanner(conn.getInputStream());
            StringBuilder jsonBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                jsonBuilder.append(scanner.nextLine());
            }
            scanner.close();

            JSONObject json = new JSONObject(jsonBuilder.toString());
            if (json.has("extract")) {
                return json.getString("extract");
            } else {
                return "Ничего не найдено по запросу.";
            }

        } catch (Exception ex) {
            return "Ошибка при выполнении запроса: " + ex.getMessage();
        }
    }
}

