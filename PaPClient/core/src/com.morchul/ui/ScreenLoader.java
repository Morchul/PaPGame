package com.morchul.ui;

import com.badlogic.gdx.Game;
import com.morchul.PaPHelper;
import com.morchul.ui.screens.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ScreenLoader {

    private static Game game;
    private static Logger log = LoggerFactory.getLogger(PaPHelper.class);
    private static CustomScreen current;

    public ScreenLoader(Game g) {
    game = g;
  }

    private static CustomScreen load(Screens screen){
        switch (screen){
            case LOGIN: return LoginScreen.getInstance();
            case HOME:
                GameMasterScreen.disposeScreen();
                GameScreen.disposeScreen();
                AllTableScreen.disposeScreen();
                return HomeScreen.getInstance();
            case SETTINGS: return SettingsScreen.getInstance();
            case CREATE_GAME: return CreateGameScreen.getInstance();
            case CREATE_CHARACTER: return CreateCharacterScreen.getInstance();
            case LOBBY: AllTableScreen.getInstance();
                return LobbyScreen.getInstance();
            case GAME_MASTER: return GameMasterScreen.getInstance();
            case GAME: return GameScreen.getInstance();
            case ALL_TABLE: return AllTableScreen.getInstance();
            default: return LoginScreen.getInstance();
        }
    }

    public static void setStatus(String s){
        log.info("Set status: " + s);
        current.setStatus(s);
    }

    public static void changeScreen(Screens screen){
        current = load(screen);
        log.info("Change Screen to: " + current.getClass().getSimpleName());
        game.setScreen(current);
    }


     public enum Screens{
         LOGIN,
         HOME,
         SETTINGS,
         CREATE_GAME,
         CREATE_CHARACTER,
         LOBBY,
         GAME_MASTER,
         GAME,
         ALL_TABLE,
     }
}