package com.morchul.desktop;


import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.morchul.PaPServer;
import org.apache.log4j.PropertyConfigurator;

public class DesktopLauncher {
    public static void main (String[] arg) {
        PropertyConfigurator.configure("log4j.properties");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new PaPServer(), config);
    }
}
