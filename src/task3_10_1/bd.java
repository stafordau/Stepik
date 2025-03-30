package task3_10_1;

import java.sql.*;

class bd {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:My_cats.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createTypesTable(conn);
                insert_type(conn, "Абиссинская кошка");
                insert_type(conn, "Австралийский мист");
                insert_type(conn, "Американская жесткошёрстная");
                System.out.println("Таблица создана и данные добавлены!");
            }
        } catch (SQLException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createTypesTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS types (
                id INTEGER PRIMARY KEY,
                type VARCHAR(100) NOT NULL
            );
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void insert_type(Connection conn, String type) throws SQLException {
        String sql = "INSERT INTO types(type) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, type);
            pstmt.executeUpdate();
        }
    }
}