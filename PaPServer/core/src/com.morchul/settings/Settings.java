package com.morchul.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private static final String PORT = "port";
    private static final int DEFAULT_PORT = 8267;

    private static final String DATABASE = "database";
    private static final String DEFAULT_DATABASE = "mongoDB";

    private static final String DATABASE_PORT = "databasePort";
    private static final int DEFAULT_DATABASE_PORT = 27017;

    private static final String DATABASE_HOST = "databaseHost";
    private static final String DEFAULT_DATABASE_HOST = "localhost";

    private static final String DATABASE_NAME = "databaseName";
    private static final String DEFAULT_DATABASE_NAME = "game";

    private static Preferences setting = Gdx.app.getPreferences("serverSettings");

    public static String getDatabase(){return setting.getString(DATABASE, DEFAULT_DATABASE);}

    public static int getPort(){
        return setting.getInteger(PORT, DEFAULT_PORT);
    }

    public static int getDatabasePort(){
        return setting.getInteger(DATABASE_PORT, DEFAULT_DATABASE_PORT);
    }

    public static String getDatabaseHost(){
        return setting.getString(DATABASE_HOST, DEFAULT_DATABASE_HOST);
    }

    public static String getDatabaseName(){
        return setting.getString(DATABASE_NAME, DEFAULT_DATABASE_NAME);
    }
}
