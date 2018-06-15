package com.morchul.database;

import org.json.JSONObject;

import java.util.List;

public interface Database {
    void logout(String username);
    boolean login(String username, String password);
    void createCharacter(JSONObject creatures, String username);
    List<JSONObject> loadCharacters(String username);
    void saveCharacter(JSONObject creatures, String username);
}
