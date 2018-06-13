package com.morchul.game;

import com.morchul.PaPHelper;
import com.morchul.model.player.User;
import com.morchul.ui.ScreenLoader;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleClientGame extends SimpleGame {

    private boolean IamGameMaster;
    private Logger log = LoggerFactory.getLogger(PaPHelper.class);

    public SimpleClientGame(String name, User gameMaster, ObservableList<User> player, String gameNumber, boolean imGameMaster) {
        super(name, gameMaster, player, gameNumber);
        this.IamGameMaster = imGameMaster;
    }

    public SimpleClientGame(GameModel model, boolean imGameMaster){
        super(model.gameName, model.gameMaster, model.players, model.gameNumber);
        this.IamGameMaster = imGameMaster;
    }

    @Override
    public void removePlayer(User user){
        ScreenLoader.setStatus("User: " + user.getUserName() + " leaf game");
        removePlayer(user.getUserUUID());
    }

    @Override
    public boolean IamTheGameMaster() {
        return IamGameMaster;
    }

}
