package com.morchul.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Settings {

    private static final String COLLECTIONS_PATH = "collectionsPath";
    private static final String DEFAULT_COLLECTIONS_PATH = Gdx.files.getExternalStoragePath();

    private static final String PORT = "port";
    private static final int DEFAULT_PORT = 8267;

    private static final String HOST = "host";
    private static final String DEFAULT_HOST = "localhost";

    private static Preferences setting = Gdx.app.getPreferences("settings");

    public static String getCollectionsPath(){
        return setting.getString(COLLECTIONS_PATH,DEFAULT_COLLECTIONS_PATH);
    }

    public static void setCollectionsPath(String s){
        setting.putString(COLLECTIONS_PATH, s);
    }
    public static int getPort(){
        return setting.getInteger(PORT, DEFAULT_PORT);
    }

    public static void setPort(int port){
        setting.putInteger(PORT, port);
    }

    public static String getHost(){
        return setting.getString(HOST, DEFAULT_HOST);
    }

    public static void setHost(String s){
        setting.putString(HOST, s);
    }

    public static void save(){
        setting.flush();
    }
}
