package task4_4_4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Map extends Application {
    private static final String LAT = "55.796293";
    private static final String LON = "49.112594";

    private static final String API_KEY = "9997e133-dc90-4966-bd9c-f3dac236ee0e";

    private WebView webView;

    @Override
    public void start(Stage stage) {
        webView = new WebView();

        Slider zoomSlider = new Slider(1, 17, 10);
        zoomSlider.setShowTickLabels(true);
        zoomSlider.setShowTickMarks(true);
        zoomSlider.setMajorTickUnit(2);
        zoomSlider.setMinorTickCount(1);
        zoomSlider.setSnapToTicks(true);

        zoomSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            int zoomLevel = newVal.intValue();
            updateMap(zoomLevel);
        });

        updateMap((int) zoomSlider.getValue());

        BorderPane root = new BorderPane();
        root.setCenter(webView);
        BorderPane.setMargin(zoomSlider, new Insets(10));
        root.setBottom(zoomSlider);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Казань — Масштабируемая карта");
        stage.setScene(scene);
        stage.show();
    }

    private void updateMap(int zoom) {
        String url = String.format(
                "https://static-maps.yandex.ru/1.x/?lang=ru_RU&ll=%s,%s&z=%d&size=650,450&l=map&apikey=%s",
                LON, LAT, zoom, API_KEY
        );
        webView.getEngine().load(url);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
