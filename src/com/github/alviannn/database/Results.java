package com.github.alviannn.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Results implements AutoCloseable {

    private final PreparedStatement statement;
    private final ResultSet set;

    public Results(PreparedStatement statement, ResultSet set) {
        this.statement = statement;
        this.set = set;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return set;
    }

    @Override
    public void close() {
        try {
            set.close();
        } catch (Exception ignored) {
        }
    }
}
