package com.morchul;

import com.badlogic.gdx.Gdx;
import com.morchul.collection.Collections;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.game.Game;
import com.morchul.model.Type;
import com.morchul.model.models.Creatures;
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

    public static List<Creatures> characters = new ArrayList<>();
    public static Collections collections;
    private static Logger log = LoggerFactory.getLogger(PaPHelper.class);

    static {
        //DEBUG Constructor TODO remove
        characters.add(new Creatures("Archer", "uuidPlayer1","","","Description", "", new Type(Type.PLAYER), 50,50,10,20,5,5,5,5,false));
        characters.add(new Creatures("Warrior", "uuidPlayer2","","","Description", "", new Type(Type.PLAYER), 60,60,10,10,4,1,6,5,false));
    }

    public static Creatures getCharacter(String s){
        for(Creatures c: characters){
            if(c.getName().equals(s)) {
                return c;
            }
        }
        return null;
    }

    public static void leafGame(){
        log.info("Leaf Game " + Self.game.getGameNumber());
        StaticServerInterface.stopListen();
        Self.game = null;
        Gdx.app.postRunnable(() -> ScreenLoader.changeScreen(ScreenLoader.Screens.HOME));
    }

    public static void loadCollection(){
        log.info("Load Collections");
        collections = new Collections(Settings.getCollectionsPath());
    }
}
