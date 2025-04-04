package task4_3_6;

import java.io.BufferedReader;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class Translator {
    private static final String CLIENT_ID = "FREE_TRIAL_ACCOUNT";
    private static final String CLIENT_SECRET = "PUBLIC_SECRET";
    private static final String ENDPOINT = "http://api.whatsmate.net/v1/translation/translate";

    public static void main(String[] args) throws Exception {
        translate("ru", "en", "Этот день в истории");
        translate("ru", "en", "Викторина");
    }

    public static void translate(String fromLang, String toLang, String text) throws Exception {
        String jsonPayload = new StringBuilder()
                .append("{")
                .append("\"fromLang\":\"").append(fromLang).append("\",")
                .append("\"toLang\":\"").append(toLang).append("\",")
                .append("\"text\":\"").append(text).append("\"")
                .append("}")
                .toString();

        URL url = new URL(ENDPOINT);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("X-WM-CLIENT-ID", CLIENT_ID);
        conn.setRequestProperty("X-WM-CLIENT-SECRET", CLIENT_SECRET);
        conn.setRequestProperty("Content-Type", "application/json");

        OutputStream os = conn.getOutputStream();
        os.write(jsonPayload.getBytes());
        os.flush();
        os.close();

        int statusCode = conn.getResponseCode();
        System.out.println("Перевод фразы: " + text);

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (statusCode == 200) ? conn.getInputStream() : conn.getErrorStream()
        ));

        String output;
        while ((output = br.readLine()) != null) {
            System.out.println("Результат: " + output + "\n");
        }

        conn.disconnect();
    }
}