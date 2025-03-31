package task4_3_1;

import org.jdatepicker.impl.*;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.Calendar;
import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class Fact {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Fact::createUI);
    }

    public static void createUI() {
        JFrame frame = new JFrame("Дата-факт");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        UtilDateModel model = new UtilDateModel();
        Properties props = new Properties();
        props.put("text.today", "Сегодня");
        props.put("text.month", "Месяц");
        props.put("text.year", "Год");

        JDatePanelImpl datePanel = new JDatePanelImpl(model, props);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        JButton button = new JButton("Получить факт");
        JTextArea output = new JTextArea(3, 20);
        output.setLineWrap(true);
        output.setWrapStyleWord(true);
        output.setEditable(false);

        button.addActionListener(e -> {
            Date selected = (Date) datePicker.getModel().getValue();
            if (selected == null) {
                output.setText("Выберите дату.");
                return;
            }

            Calendar selectedDate = Calendar.getInstance();
            selectedDate.setTime(selected);

            if (selectedDate == null) {
                output.setText("Выберите дату.");
                return;
            }

            int day = selectedDate.get(Calendar.DAY_OF_MONTH);
            int month = selectedDate.get(Calendar.MONTH) + 1;

            String url = "http://numbersapi.com/" + month + "/" + day + "/date";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .GET()
                        .build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());

                output.setText(response.body());
            } catch (IOException | InterruptedException ex) {
                output.setText("Ошибка загрузки данных.");
                ex.printStackTrace();
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(datePicker, BorderLayout.NORTH);
        panel.add(button, BorderLayout.CENTER);
        panel.add(output, BorderLayout.SOUTH);

        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }
}

class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormat.parseObject(text);
    }

    @Override
    public String valueToString(Object value) {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormat.format(cal.getTime());
        }
        return "";
    }
}