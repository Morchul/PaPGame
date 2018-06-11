package com.morchul.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private static final String PORT = "port";
    private static final int DEFAULT_PORT = 8267;

    private static final String DATABASE_PORT = "databasePort";
    private static final int DEFAULT_DATABASE_PORT = 27017;

    private static final String DATABASE_HOST = "databaseHost";
    private static final String DEFAULT_DATABASE_HOST = "localhost";

    private static final String DATABASE_NAME = "databaseName";
    private static final String DEFAULT_DATABASE_NAME = "game";

    private static final String COLLECTION_NAME = "collectionName";
    private static final String DEFAULT_COLLECTION_NAME = "player";

    private static Preferences setting = Gdx.app.getPreferences("serverSettings");

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

    public static String getCollectionName(){
        return setting.getString(COLLECTION_NAME, DEFAULT_COLLECTION_NAME);
    }
}
