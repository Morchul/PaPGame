package com.morchul.game;

import com.morchul.connection.message.MessageModelCreator;
import com.morchul.model.player.User;
import javafx.collections.FXCollections;

public class SimpleServerGame extends SimpleGame {

    private GameWrapper wrapper;

    public SimpleServerGame(User gameMaster,String name) {
        super(name, gameMaster, FXCollections.observableArrayList(), GameManager.generateGameNumber());
        player.add(gameMaster);
    }

    private GameWrapper wrapper(){
        if(wrapper == null)
            wrapper = GameManager.getGameWrapper(gameNumber);
        return wrapper;
    }

    @Override
    public void addPlayer(User user){
        GameWrapper w = wrapper();
        w.sendToAll(MessageModelCreator.createPlayerJoinGameMessage(user));
        super.addPlayer(user);
    }

    @Override
    public void removePlayer(User user){
        if (user == gameMaster) {
            gameMaster = null;
            destroyGame();
            player.clear();
            GameManager.removeGame(wrapper());
        } else {
            player.remove(user);
            wrapper().sendToAll(MessageModelCreator.createPlayerLeafGameMessage(user));
        }
    }

    @Override
    public boolean IamTheGameMaster() {
        return false;
    }

    private synchronized void destroyGame(){
        wrapper().sendToAll(MessageModelCreator.createCloseGameMessage());
        wrapper().destroyGame();
    }
}
