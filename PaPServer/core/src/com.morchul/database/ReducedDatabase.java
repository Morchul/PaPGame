package com.morchul.database;

/**
 * Used when no other store is available
 */
public class ReducedDatabase implements Database {

    @Override
    public void logout(String username) { }

    @Override
    public boolean login(String username, String password) { return true; }
}
