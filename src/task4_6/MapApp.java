package task4_6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapApp extends Application {
    private static final String API_KEY = "6b788a57-9de4-4579-8fc0-ba8eaaaa9f93";

    private TextField searchField;
    private ListView<SearchResult> resultsList;
    private TextArea detailInfo;
    private WebView mapView;
    private Slider zoomSlider;
    private RadioButton mapMode;
    private RadioButton satMode;
    private CheckBox trafficBox;

    private List<SearchResult> searchResults = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Yandex Maps Project");

        searchField = new TextField();
        searchField.setPromptText("Search");

        Button searchButton = new Button("поиск!");
        searchButton.setOnAction(e -> performSearch());

        resultsList = new ListView<>();
        resultsList.setCellFactory(new Callback<>() {
            @Override
            public ListCell<SearchResult> call(ListView<SearchResult> param) {
                return new ListCell<>() {
                    @Override
                    protected void updateItem(SearchResult item, boolean empty) {
                        super.updateItem(item, empty);
                        setText(empty || item == null ? null : item.name + " (топоним)");
                    }
                };
            }
        });
        resultsList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> displayDetails(newVal));

        detailInfo = new TextArea();
        detailInfo.setEditable(false);

        mapView = new WebView();

        zoomSlider = new Slider(1, 17, 10);
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setMajorTickUnit(4);
        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateMap());

        mapMode = new RadioButton("Схема");
        satMode = new RadioButton("Спутник");
        ToggleGroup mapGroup = new ToggleGroup();
        mapMode.setToggleGroup(mapGroup);
        satMode.setToggleGroup(mapGroup);
        mapMode.setSelected(true);
        mapMode.setOnAction(e -> updateMap());
        satMode.setOnAction(e -> updateMap());

        trafficBox = new CheckBox("Пробки");
        trafficBox.setOnAction(e -> updateMap());

        HBox topControls = new HBox(10, searchField, searchButton);
        VBox leftPanel = new VBox(10, topControls, resultsList, detailInfo);
        leftPanel.setPadding(new Insets(10));
        leftPanel.setPrefWidth(300);

        HBox mapControls = new HBox(10, mapMode, satMode, trafficBox);
        mapControls.setPadding(new Insets(10));
        VBox rightPanel = new VBox(10, mapView, zoomSlider, mapControls);
        rightPanel.setPadding(new Insets(10));
        rightPanel.setVgrow(mapView, Priority.ALWAYS);

        HBox root = new HBox(leftPanel, rightPanel);

        Scene scene = new Scene(root, 1000, 600);
        stage.setScene(scene);
        stage.show();
    }

    private void performSearch() {
        try {
            String query = URLEncoder.encode(searchField.getText(), StandardCharsets.UTF_8);
            String urlStr = String.format(
                    "https://geocode-maps.yandex.ru/1.x/?format=json&apikey=%s&geocode=%s",
                    API_KEY, query);
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) response.append(line);
            reader.close();

            JSONObject json = new JSONObject(response.toString());
            JSONArray items = json.getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember");

            searchResults.clear();
            for (int i = 0; i < items.length(); i++) {
                JSONObject obj = items.getJSONObject(i).getJSONObject("GeoObject");
                String name = obj.getJSONObject("metaDataProperty")
                        .getJSONObject("GeocoderMetaData").getString("text");
                String pos = obj.getJSONObject("Point").getString("pos");
                searchResults.add(new SearchResult(name, pos));
            }
            resultsList.getItems().setAll(searchResults);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayDetails(SearchResult result) {
        if (result == null) return;
        detailInfo.setText("Название: " + result.name + "\nКоординаты: " + result.pos);
        updateMap();
    }

    private void updateMap() {
        SearchResult selected = resultsList.getSelectionModel().getSelectedItem();
        if (selected == null) return;

        String[] coords = selected.pos.split(" ");
        String lon = coords[0];
        String lat = coords[1];
        int zoom = (int) zoomSlider.getValue();
        String layer = mapMode.isSelected() ? "map" : "sat";
        String traffic = trafficBox.isSelected() ? ",trf" : "";

        String url = String.format(
                "https://static-maps.yandex.ru/1.x/?ll=%s,%s&z=%d&l=%s%s&pt=%s,%s,pm2rdl",
                lon, lat, zoom, layer, traffic, lon, lat);
        mapView.getEngine().load(url);
    }

    static class SearchResult {
        String name;
        String pos;

        public SearchResult(String name, String pos) {
            this.name = name;
            this.pos = pos;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
