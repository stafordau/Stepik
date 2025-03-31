package task4_4_1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Map extends Application {
    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://static-maps.yandex.ru/v1?lang=ru_RU&ll=30.314566,59.9398648&z=9&spn=0.01,0.001&size=650,450&apikey=9997e133-dc90-4966-bd9c-f3dac236ee0e");

        Scene scene = new Scene(webView, 800, 600);
        stage.setTitle("Эрмитаж на карте (Яндекс)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}