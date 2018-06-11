package com.morchul.ui.screens;

import com.badlogic.gdx.Screen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface CustomScreen extends Screen {

    Logger log = LoggerFactory.getLogger(CustomScreen.class);

    void setStatus(String status);
}
