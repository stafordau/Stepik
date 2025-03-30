package task3_10_1;

import java.sql.*;
import java.io.*;

public class bd {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:My_cats.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            createTable(conn);
            createCatsTable(conn);

            insertInitialTypes(conn);
            insertTypesFromFormattedFile(conn, "src/task3_10_1/types.txt");

            update_type(conn, 1, "Абиссинская кошка (обновлено)");
            delete_type(conn, 2);

            System.out.println("\nget_type(1): " + get_type(conn, 1));
            System.out.println("\nget_type_where(\"id < 5\"):");
            get_type_where(conn, "id < 5");

            System.out.println("\nВсе породы котиков:");
            get_all_types(conn);

            insert_cat(conn, "Барсик", "Абиссинская кошка", 2, 4.5);
            insert_cat(conn, "Мурка", "Сибирская кошка", 3, 5.1);
            insert_cat(conn, "Снежок", "Футуристическая лысая", 1, 3.2);


        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void createTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS types (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    type TEXT NOT NULL
                );
                """;
        conn.createStatement().execute(sql);
    }

    private static void createCatsTable(Connection conn) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS cats (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name VARCHAR(20) NOT NULL,
                    type_id INTEGER NOT NULL,
                    age INTEGER NOT NULL,
                    weight DOUBLE NOT NULL,
                    FOREIGN KEY (type_id) REFERENCES types(id)
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

    public static void delete_type(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM types WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Тип с id = " + id + " удалён.");
            } else {
                System.out.println("Тип с id = " + id + " не найден.");
            }
        }
    }

    public static void update_type(Connection conn, int id, String new_type) throws SQLException {
        String sql = "UPDATE types SET type = ? WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, new_type);
            pstmt.setInt(2, id);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Тип с id = " + id + " обновлён на: " + new_type);
            } else {
                System.out.println("Тип с id = " + id + " не найден.");
            }
        }
    }

    public static String get_type(Connection conn, int id) throws SQLException {
        String sql = "SELECT type FROM types WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("type");
                } else {
                    return "Тип с id = " + id + " не найден.";
                }
            }
        }
    }

    public static void get_type_where(Connection conn, String where) throws SQLException {
        String sql = "SELECT id, type FROM types WHERE " + where;
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String type = rs.getString("type");
                System.out.println(id + ": " + type);
            }
        }
    }

    public static void get_all_types(Connection conn) throws SQLException {
        get_type_where(conn, "1 = 1");
    }

    public static void insert_cat(Connection conn, String name, String type, int age, Double weight) throws SQLException {
        int typeId = get_or_insert_type(conn, type);
        String sql = "INSERT INTO cats(name, type_id, age, weight) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, typeId);
            pstmt.setInt(3, age);
            pstmt.setDouble(4, weight);
            pstmt.executeUpdate();
            System.out.println("Кот \"" + name + "\" добавлен в базу данных.");
        }
    }

    private static int get_or_insert_type(Connection conn, String typeName) throws SQLException {
        String selectSql = "SELECT id FROM types WHERE type = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectSql)) {
            selectStmt.setString(1, typeName);
            ResultSet rs = selectStmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        }

        String insertSql = "INSERT INTO types(type) VALUES (?)";
        try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
            insertStmt.setString(1, typeName);
            insertStmt.executeUpdate();
            ResultSet keys = insertStmt.getGeneratedKeys();
            if (keys.next()) {
                System.out.println("Добавлен новый тип: " + typeName);
                return keys.getInt(1);
            } else {
                throw new SQLException("Ошибка при получении id нового типа.");
            }
        }
    }

}