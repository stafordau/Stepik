package task3_8_1;

import java.sql.*;

public class BookDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:books.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createGenresTable(conn);
                insertGenre(conn, "Антиутопия");
                insertGenre(conn, "Фантастика");

                createBooksTable(conn);
                insertBook(conn, "1984", 1949, "Джордж Оруэлл", 1);
                insertBook(conn, "О дивный новый мир", 1932, "Хаксли Олдос Леонард", 1);
                insertBook(conn, "451' по Фаренгейту", 1953, "Рэй Брэдбери", 1);

                System.out.println("База данных, жанры и книги успешно добавлены.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void createGenresTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS genres (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                genre TEXT NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertGenre(Connection conn, String genre) throws SQLException {
        String sql = "INSERT INTO genres(genre) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, genre);
            pstmt.executeUpdate();
        }
    }

    private static void createBooksTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS books (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                year INTEGER,
                author TEXT,
                genre_id INTEGER,
                FOREIGN KEY (genre_id) REFERENCES genres(id)
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insertBook(Connection conn, String title, int year, String author, int genreId) throws SQLException {
        String sql = "INSERT INTO books(title, year, author, genre_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setInt(2, year);
            pstmt.setString(3, author);
            pstmt.setInt(4, genreId);
            pstmt.executeUpdate();
        }
    }
}