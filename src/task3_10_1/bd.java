package task3_10_1;

import java.sql.*;

class bd {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:My_cats.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createTypesTable(conn);
                System.out.println("Создано!");
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
}