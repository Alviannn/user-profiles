package com.github.alviannn;

import com.github.alviannn.database.Database;
import com.github.alviannn.database.UserHandler;
import com.github.alviannn.menus.LoginMenu;
import com.github.alviannn.models.User;

public class Main {

    public static UserHandler USER_HANDLER;

    public static void main(String[] args) {
        Database database = new Database();
        database.connect();

        USER_HANDLER  = new UserHandler(database);

        USER_HANDLER.registerUser(new User("admin", "admin@admin.com", "me smart"));

        USER_HANDLER.registerUser(new User("alvian", "alvian@email.com", "alvian123"));
        USER_HANDLER.registerUser(new User("julian", "julian@email.com", "julian123"));
        USER_HANDLER.registerUser(new User("lia", "lia@email.com", "lia123"));

        new LoginMenu();
    }

}
