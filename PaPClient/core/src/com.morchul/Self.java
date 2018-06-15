package com.morchul;

import com.badlogic.gdx.Gdx;
import com.morchul.collection.Collections;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.game.Game;
import com.morchul.model.ClientImpCreatures;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.player.User;
import com.morchul.settings.Settings;
import com.morchul.ui.ScreenLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Self {

    public static User user;
    public static Game game;

    public static List<Creatures> characterList = new ArrayList<>();
    public static Collections collections;
    private static Logger log = LoggerFactory.getLogger(PaPHelper.class);

//    static {
//        //DEBUG Constructor TODO remove
//        characterList.add(new ClientImpCreatures("Archer", "uuidPlayer1","gameUUIDPlayer1","","Description", "", new Type(Type.PLAYER), 50,50,10,20,5,5,5,5,false));
//        characterList.add(new ClientImpCreatures("Warrior", "uuidPlayer2","gameUUIDPlayer2","","Description", "", new Type(Type.PLAYER), 60,60,10,10,4,1,6,5,false));
//    }

    public static void addCharacters(List<Creatures> creatures){
        characterList.addAll(creatures);
    }

    public static Creatures getCharacter(String s){
        for(Creatures c: characterList){
            if(c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    public static void leafGame(String reason){
        log.info("Leaf Game " + Self.game.getGameNumber());
        StaticServerInterface.stopListen();
        Self.game = null;
        Gdx.app.postRunnable(() -> {
            ScreenLoader.changeScreen(ScreenLoader.Screens.HOME);
            ScreenLoader.setStatus(reason);
        });
    }

    public static void leafGame(){
        leafGame("");
    }

    public static void loadCollection(){
        log.info("Load Collections");
        collections = new Collections(Settings.getCollectionsPath());
    }
}
