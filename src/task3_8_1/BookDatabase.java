package task3_8_1;

import java.sql.*;

public class BookDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:books.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createTable(conn);
                insertBook(conn, "1984", 1949, "Джордж Оруэлл");
                insertBook(conn, "О дивный новый мир", 1932, "Хаксли Олдос Леонард");
                insertBook(conn, "451' по Фаренгейту", 1953, "Рэй Брэдбери");
                System.out.println("База данных и книги успешно добавлены.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                year INTEGER,
                author TEXT
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertBook(Connection conn, String title, int year, String author) throws SQLException {
        String sql = "INSERT INTO books(title, year, author) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setString(3, author);
            pstmt.executeUpdate();
        }
    }
}