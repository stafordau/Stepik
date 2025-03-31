package task4_4_3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Map extends Application {
    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://static-maps.yandex.ru/v1?lang=ru_RU" +
                "&ll=-61.617077,27.658119" +
                "&spn=10,20" +
                "&size=650,450" +
                "&pl=c:ffc0cb,w:5,-66.431861,18.221466,-64.780471,32.301763,-80.377401,26.341239,-66.431861,18.221466" +
                "&apikey=9997e133-dc90-4966-bd9c-f3dac236ee0e");

        Scene scene = new Scene(webView, 800, 600);
        stage.setTitle("Бермудский треугольник");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
