package task3_8_1;

import java.sql.*;

public class BookDatabase {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:books.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                enableForeignKeys(conn);
                createGenresTable(conn);
                insertGenre(conn, "Антиутопия");
                insertGenre(conn, "Фантастика");

                createBooksTable(conn);
                insertBook(conn, "1984", 1949, "Джордж Оруэлл", 1);
                insertBook(conn, "О дивный новый мир", 1932, "Хаксли Олдос Леонард", 1);
                insertBook(conn, "451' по Фаренгейту", 1953, "Рэй Брэдбери", 1);

                manageGenres(conn);

                System.out.println("База данных, жанры и книги успешно добавлены.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void enableForeignKeys(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute("PRAGMA foreign_keys = ON");
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

    // 🔄 Добавление и удаление жанров
    private static void manageGenres(Connection conn) throws SQLException {
        int newGenreId = insertGenreReturnId(conn, "Детектив");
        System.out.println("Добавлен жанр 'Детектив' с id = " + newGenreId);

        deleteGenre(conn, newGenreId);

        deleteGenre(conn, 1);
    }

    private static int insertGenreReturnId(Connection conn, String genre) throws SQLException {
        String sql = "INSERT INTO genres(genre) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, genre);
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    private static void deleteGenre(Connection conn, int genreId) {
        String sql = "DELETE FROM genres WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, genreId);
            pstmt.executeUpdate();
            System.out.println("Жанр с id = " + genreId + " удалён.");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении жанра с id = " + genreId + ": " + e.getMessage());
        }
    }
}