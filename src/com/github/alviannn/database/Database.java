package com.github.alviannn.database;

import java.sql.*;

public class Database {

    private Connection connection;

    public Database() {
        this.connect();

        try (Results res = this.getResults("SELECT * FROM users WHERE username = ?;", "kevin")){
            ResultSet set = res.getResultSet();
            while (set.next()) {
                System.out.println(set.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.execute("DELETE FROM users WHERE id = ?;", 3);
    }

    public Results getResults(String sql, Object ...args) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            statement.setObject(i + 1, args[i]);
        }

        return new Results(statement, statement.executeQuery());
    }

    public boolean execute(String sql, Object ...args) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setObject(i + 1, args[i]);
            }
            return statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/bncc-java";

        try {
            connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Database berhasil terkoneksi");
        } catch (SQLException e) {
            System.out.println("Database gagal terkoneksi");
            e.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Database();
    }

}
