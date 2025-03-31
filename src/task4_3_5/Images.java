package task4_3_5;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import javax.swing.*;
import net.miginfocom.swing.*;
import org.json.JSONObject;


class Images extends JFrame {
    public Images() {
        initComponents();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents  @formatter:off
        labelImg = new JLabel();
        dogButton = new JButton();
        foxButton = new JButton();

        //======== this ========
        setMinimumSize(new Dimension(800, 600));
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
                "fill,gapx 50",
                // columns
                "[fill]" +
                        "[fill]" +
                        "[fill]",
                // rows
                "[]" +
                        "[]" +
                        "[]" +
                        "[]"));

        //---- labelImg ----
        labelImg.setMinimumSize(new Dimension(583, 540));
        contentPane.add(labelImg, "cell 2 0 1 4");

        //---- dogButton ----
        dogButton.setText("\u0421\u043e\u0431\u0430\u0447\u043a\u0443!");
        contentPane.add(dogButton, "cell 0 1,aligny bottom,growy 0");

        //---- foxButton ----
        foxButton.setText("\u041b\u0438\u0441\u0438\u0447\u043a\u0443!");
        contentPane.add(foxButton, "cell 0 2,aligny top,growy 0");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents  @formatter:on

        dogButton.addActionListener(e -> takeDog());
        foxButton.addActionListener(e -> takeFox());
    }

    private JButton dogButton;
    private JLabel labelImg;
    private JButton foxButton;

    private void takeDog() {
        String url = "https://random.dog/woof.json";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject obj = new JSONObject(response.body());
            String imageUrl = obj.getString("url");

            if (!imageUrl.endsWith(".jpg") && !imageUrl.endsWith(".jpeg") &&
                    !imageUrl.endsWith(".png") && !imageUrl.endsWith(".webp")) {
                labelImg.setText("Получен не-изображение: " + imageUrl);
                labelImg.setIcon(null);
                return;
            }

            ImageIcon icon = new ImageIcon(new URL(imageUrl));

            Image scaled = icon.getImage().getScaledInstance(
                    labelImg.getWidth(),
                    labelImg.getHeight(),
                    Image.SCALE_SMOOTH
            );

            labelImg.setIcon(new ImageIcon(scaled));
            labelImg.setText("");
        } catch (IOException | InterruptedException ex) {
            labelImg.setText("Ошибка загрузки данных.");
            ex.printStackTrace();
        }
    }

    private void takeFox() {
        String url = "https://randomfox.ca/floof/";

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject obj = new JSONObject(response.body());
            String imageUrl = obj.getString("image");

            if (!imageUrl.endsWith(".jpg") && !imageUrl.endsWith(".jpeg") &&
                    !imageUrl.endsWith(".png") && !imageUrl.endsWith(".webp")) {
                labelImg.setText("Получен не-изображение: " + imageUrl);
                labelImg.setIcon(null);
                return;
            }

            ImageIcon icon = new ImageIcon(new URL(imageUrl));

            Image scaled = icon.getImage().getScaledInstance(
                    labelImg.getWidth(),
                    labelImg.getHeight(),
                    Image.SCALE_SMOOTH
            );

            labelImg.setIcon(new ImageIcon(scaled));
            labelImg.setText("");
        } catch (IOException | InterruptedException ex) {
            labelImg.setText("Ошибка загрузки данных.");
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Images editor = new Images();
            editor.setTitle("Картинки");
            editor.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            editor.setSize(800, 600);
            editor.setLocationRelativeTo(null);
            editor.setVisible(true);
        });
    }
}