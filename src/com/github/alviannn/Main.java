package com.github.alviannn;

import com.github.alviannn.menus.LoginMenu;
import com.github.alviannn.models.User;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static List<User> USER_LIST = new ArrayList<>();

    public static boolean doesUserExists(String username, String email) {
        boolean found = false;

        for (User user : Main.USER_LIST) {
            found = user.getUsername().equals(username) || user.getEmail().equals(email);
            if (found) {
                break;
            }
        }

        return found;
    }

    public static User findUser(String username) {
        for (User user : Main.USER_LIST) {
            boolean found = user.getUsername().equals(username);

            if (found) {
                return user;
            }
        }

        return null;
    }

    public static void main(String[] args) {
        USER_LIST.add(new User("admin", "admin@admin.com", "me smart"));

        USER_LIST.add(new User("alvian", "alvian@email.com", "alvian123"));
        USER_LIST.add(new User("julian", "julian@email.com", "julian123"));
        USER_LIST.add(new User("lia", "lia@email.com", "lia123"));

        new LoginMenu();
    }

}
