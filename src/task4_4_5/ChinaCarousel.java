package task4_4_5;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class ChinaCarousel extends Application {

    private final List<Place> places = List.of(
            new Place("Великая Китайская стена", "116.5703749", "40.4319077"),
            new Place("Запретный город, Пекин", "116.397389", "39.917255"),
            new Place("Терракотовая армия", "109.2924", "34.3853"),
            new Place("Парк Чжанцзяцзе", "110.4792", "29.3422"),
            new Place("Гора Хуашань", "110.0848", "34.4833")
    );

    private int currentIndex = 0;
    private WebView webView;
    private Timeline timeline;

    @Override
    public void start(Stage stage) {
        webView = new WebView();

        Button prevButton = new Button("← Назад");
        Button nextButton = new Button("Вперёд →");

        prevButton.setOnAction(e -> {
            stopAutoSwitch();
            showPrevious();
        });

        nextButton.setOnAction(e -> {
            stopAutoSwitch();
            showNext();
        });

        HBox buttonBox = new HBox(10, prevButton, nextButton);
        buttonBox.setPadding(new Insets(10));
        buttonBox.setStyle("-fx-alignment: center;");

        BorderPane root = new BorderPane();
        root.setCenter(webView);
        root.setBottom(buttonBox);

        Scene scene = new Scene(root, 800, 600);
        stage.setTitle("Китайская карусель");
        stage.setScene(scene);
        stage.show();

        showPlace(currentIndex);

        timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> showNext()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void showNext() {
        currentIndex = (currentIndex + 1) % places.size();
        showPlace(currentIndex);
    }

    private void showPrevious() {
        currentIndex = (currentIndex - 1 + places.size()) % places.size();
        showPlace(currentIndex);
    }

    private void showPlace(int index) {
        Place place = places.get(index);
        String url = String.format(
                "https://static-maps.yandex.ru/1.x/?lang=ru_RU&ll=%s,%s&z=12&size=650,450&l=map&pt=%s,%s,pm2rdl",
                place.lon, place.lat, place.lon, place.lat
        );
        webView.getEngine().load(url);
    }

    private void stopAutoSwitch() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    static class Place {
        String name;
        String lon;
        String lat;

        Place(String name, String lon, String lat) {
            this.name = name;
            this.lon = lon;
            this.lat = lat;
        }
    }
}
