package com.morchul.database;

import com.morchul.settings.Settings;

public class DatabaseFactory {

    public static Database createDatabase(){
        switch (Settings.getDatabase()){
            case "mongoDB": return new MongoDB();
            case "none": return new ReducedDatabase();
            default: return new ReducedDatabase();
        }
    }
}
