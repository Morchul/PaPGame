package com.morchul.database;

import com.morchul.settings.ServerSettings;

public class DatabaseFactory {

    public static Database createDatabase(){
        switch (ServerSettings.getDatabase()){
            case "mongoDB": return new MongoDB();
            case "none": return new ReducedDatabase();
            case "file": return new FileDatabase();
            default: return new ReducedDatabase();
        }
    }
}
