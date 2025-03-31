package task4_5_1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

class Address {
    private static final String API_KEY = "6b788a57-9de4-4579-8fc0-ba8eaaaa9f93";
    private static final String API_URL = "https://geocode-maps.yandex.ru/v1/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название здания: ");
        String query = scanner.nextLine();

        try {
            String encodedQuery = URLEncoder.encode(query, "UTF-8");
            String requestUrl = API_URL + "?apikey=" + API_KEY + "&geocode=" + encodedQuery + "&lang=ru_RU&format=json";

            HttpURLConnection conn = (HttpURLConnection) new URL(requestUrl).openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder responseStr = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseStr.append(line);
            }
            reader.close();

            JSONObject json = new JSONObject(responseStr.toString());
            JSONArray results = json
                    .getJSONObject("response")
                    .getJSONObject("GeoObjectCollection")
                    .getJSONArray("featureMember");

            if (results.length() == 0) {
                System.out.println("404 not found");
            } else {
                JSONObject geoObject = results.getJSONObject(0)
                        .getJSONObject("GeoObject");

                String address = geoObject
                        .getJSONObject("metaDataProperty")
                        .getJSONObject("GeocoderMetaData")
                        .getString("text");

                System.out.println("Найденный адрес: " + address);
            }

        } catch (Exception e) {
            System.out.println("Ошибка при запросе или обработке данных.");
            e.printStackTrace();
        }
    }
}
