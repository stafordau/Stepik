package task3_10_1;

import java.sql.*;
import java.io.*;
import java.util.ArrayList;

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

            add_more_cats(conn, 5000);

            delete_cat(conn, 3);
            delete_cat(conn, "age > 15 AND weight < 4");
            update_cat(conn, 1, "weight = 5.5, age = 7", "age < 20");

            System.out.println("\nget_cat(1):");
            System.out.println(get_cat(conn, 1));

            System.out.println("\nget_cat_where(\"age < 2 AND weight > 5\"):");
            get_cat_where(conn, "age < 2 AND weight > 5");

            System.out.println("\nВсе котики:");
            get_all_cats(conn);


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

    public static void add_more_cats(Connection conn, int n) throws SQLException, IOException {
        String[] names = read_names_from_file("src/task3_10_1/names.txt");
        String[] types = load_types_from_db(conn);

        for (int i = 0; i < n; i++) {
            String name = names[(int) (Math.random() * names.length)];
            String type = types[(int) (Math.random() * types.length)];
            int age = 1 + (int) (Math.random() * 20);
            double weight = 2 + Math.random() * 6;
            insert_cat(conn, name, type, age, weight);
        }
    }

    private static String[] read_names_from_file(String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line.trim());
        }
        br.close();
        String content = sb.toString();
        int start = content.indexOf('{');
        int end = content.lastIndexOf('}');
        if (start == -1 || end == -1) return new String[0];
        String[] names = content.substring(start + 1, end).split(",");
        for (int i = 0; i < names.length; i++) {
            names[i] = names[i].trim().replaceAll("^\"|\"$", "");
        }
        return names;
    }

    private static String[] load_types_from_db(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT type FROM types");
        ArrayList<String> list = new ArrayList<>();
        while (rs.next()) {
            list.add(rs.getString("type"));
        }
        return list.toArray(new String[0]);
    }

    public static void delete_cat(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM cats WHERE id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            int affected = pstmt.executeUpdate();
            if (affected > 0) {
                System.out.println("Кот с id = " + id + " удалён.");
            } else {
                System.out.println("Кот с id = " + id + " не найден.");
            }
        }
    }

    public static void delete_cat(Connection conn, String where) throws SQLException {
        String sql = "DELETE FROM cats WHERE " + where;
        try (Statement stmt = conn.createStatement()) {
            int affected = stmt.executeUpdate(sql);
            System.out.println("Удалено котиков: " + affected);
        }
    }

    public static void update_cat(Connection conn, int id, String set, String where) throws SQLException {
        String sql = "UPDATE cats SET " + set + " WHERE id = " + id + " AND " + where;
        try (Statement stmt = conn.createStatement()) {
            int affected = stmt.executeUpdate(sql);
            if (affected > 0) {
                System.out.println("Кот с id = " + id + " обновлён.");
            } else {
                System.out.println("Кот с id = " + id + " не найден или условие не выполнено.");
            }
        }
    }

    public static String get_cat(Connection conn, int id) throws SQLException {
        String sql = """
                SELECT cats.id, cats.name, types.type, cats.age, cats.weight
                FROM cats
                JOIN types ON cats.type_id = types.id
                WHERE cats.id = ?
                """;
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return format_cat_row(rs);
                } else {
                    return "Кот с id = " + id + " не найден.";
                }
            }
        }
    }

    public static void get_cat_where(Connection conn, String where) throws SQLException {
        String sql = """
                SELECT cats.id, cats.name, types.type, cats.age, cats.weight
                FROM cats
                JOIN types ON cats.type_id = types.id
                WHERE """ + " " + where;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println(format_cat_row(rs));
            }
        }
    }

    public static void get_all_cats(Connection conn) throws SQLException {
        get_cat_where(conn, "1 = 1");
    }

    private static String format_cat_row(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String type = rs.getString("type");
        int age = rs.getInt("age");
        double weight = rs.getDouble("weight");
        return id + ": " + name + ", " + type + ", возраст: " + age + ", вес: " + weight;
    }


}