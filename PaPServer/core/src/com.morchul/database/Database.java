package com.morchul.database;

public interface Database {
    void logout(String username);
    boolean login(String username, String password);
}
