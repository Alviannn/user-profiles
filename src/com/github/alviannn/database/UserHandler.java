package com.github.alviannn.database;

import com.github.alviannn.models.User;

import java.sql.ResultSet;
import java.util.ArrayList;

public class UserHandler {

    private final Database db;

    public UserHandler(Database db) {
        this.db = db;
    }

    public void registerUser(User user) {
        db.execute("INSERT INTO users (username, email, password) VALUES (?, ?, ?);",
                user.getUsername(), user.getEmail(), user.getPassword());
    }

    public boolean doesUserExists(String username, String email) {
        try (ResultSet set = db.getResults("SELECT * FROM users WHERE username = ? OR email = ?;", username, email)) {
            // {}, {user1}, {user2}, {user3}

//            if (set.next()) {
//                return true;
//            }
            return set.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User findUser(String targetUsername) {
        try (ResultSet set = db.getResults("SELECT * FROM users WHERE username = ?;", targetUsername)) {
            if (set.next()) {
                String username = set.getString("username");
                String email = set.getString("email");
                String password = set.getString("password");

                return new User(username, email, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try (ResultSet set = db.getResults("SELECT * FROM users;")) {
            while (set.next()) {
                String username = set.getString("username");
                String email = set.getString("email");
                String password = set.getString("password");

                User user = new User(username, email, password);
                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userList;
    }

    public void deleteUser(String username) {
        db.execute("DELETE FROM users WHERE username = ?;", username);
    }

}
