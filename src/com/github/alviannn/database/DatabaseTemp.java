package com.github.alviannn.database;

import java.sql.*;

public class DatabaseTemp {

    public DatabaseTemp() {
        String url = "jdbc:mysql://localhost:3306/bncc-java";

        try {
            Connection connection = DriverManager.getConnection(url, "root", "");
            System.out.println("Database berhasil terkoneksi");

            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users;"); ResultSet set = statement.executeQuery();) {
                while (set.next()) {
                    int id = set.getInt("id");
                    String username = set.getString("username");
                    String email = set.getString("email");
                    String password = set.getString("password");

                    System.out.println(id + "." + username + ";" + email + ";" + password);
                }
            }

//            int id = 3;
//            String name = "alvian";
//            String email = "alvian@gmail.com";
//            String password = "alvian1341";

            // leo'--; DROP TABLE users;
            // INSERT INTO users (...) VALUES ('leo'--; DROP TABLE users;, ...);

//            PreparedStatement statement = connection.prepareStatement("INSERT INTO users "
//                                                                      + "(id, username, email, password) VALUES "
//                                                                      + "(?, ?, ?, ?);");
//
//            statement.setInt(1, id);
//            statement.setString(2, name);
//            statement.setString(3, email);
//            statement.setString(4, password);
//
//            statement.execute();
        } catch (SQLException e) {
            System.out.println("Database gagal terkoneksi");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DatabaseTemp();
    }

}
