package com.morchul.database;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Used when no other store is available
 */
public class ReducedDatabase implements Database {

    @Override
    public void logout(String username) { }

    @Override
    public boolean login(String username, String password) { return true; }

    @Override
    public void createCharacter(JSONObject creatures, String username) { }

    @Override
    public List<JSONObject> loadCharacters(String username) {
        return new ArrayList<>();
    }

    @Override
    public void saveCharacter(JSONObject creatures, String username) {

    }
}
