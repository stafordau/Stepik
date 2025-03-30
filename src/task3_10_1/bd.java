package task3_10_1;

import java.sql.*;
import java.io.*;

public class bd {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:My_cats.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            createTable(conn);
            insertInitialTypes(conn);
            insertTypesFromFormattedFile(conn, "src/task3_10_1/types.txt");
            System.out.println("Таблица создана и данные добавлены!");
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS types (
                    id INTEGER PRIMARY KEY,
                    type TEXT NOT NULL
                );
                """;
        conn.createStatement().execute(sql);
    }

    private static void insertInitialTypes(Connection conn) throws SQLException {
        String[] initialTypes = {
                "Абиссинская кошка",
                "Австралийский мист",
                "Американская жесткошёрстная"
        };
        for (String type : initialTypes) {
            insertType(conn, type);
        }
    }

    private static void insertType(Connection conn, String type) throws SQLException {
        String sql = "INSERT INTO types(type) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            pstmt.executeUpdate();
        }
    }

    private static void insertTypesFromFormattedFile(Connection conn, String fileName) throws IOException, SQLException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }

            String content = sb.toString();

            int start = content.indexOf('{');
            int end = content.lastIndexOf('}');
            if (start == -1 || end == -1) return;

            String[] types = content.substring(start + 1, end).split(",");

            for (String type : types) {
                type = type.trim().replaceAll("^\"|\"$", "");
                if (!type.isEmpty()) {
                    insertType(conn, type);
                }
            }
        }
    }
}