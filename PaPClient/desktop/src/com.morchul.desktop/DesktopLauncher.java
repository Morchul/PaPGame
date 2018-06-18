package com.morchul.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.morchul.PaPHelper;
import org.apache.log4j.PropertyConfigurator;

public class DesktopLauncher {
    public static void main (String[] arg) {
        PropertyConfigurator.configure("log4j.properties");
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        new LwjglApplication(new PaPHelper(), config);
    }
}
