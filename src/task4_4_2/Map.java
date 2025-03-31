package task4_4_2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Map extends Application {
    @Override
    public void start(Stage stage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://static-maps.yandex.ru/v1?lang=ru_RU" +
                "&ll=32.052517,54.790759" +
                "&pt=32.054247,54.791235,pm2pnl1~32.054247,54.790859,pm2pnl2~32.051618,54.791235,pm2pnl3~32.054764,54.789988,pm2pnl4~32.052364,54.789988,pm2pnl5" +
                "&spn=0.01,0.001" +
                "&size=650,450" +
                "&apikey=9997e133-dc90-4966-bd9c-f3dac236ee0e");

        Scene scene = new Scene(webView, 800, 600);
        stage.setTitle("Смоленская крепостная стена");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
