package com.morchul.game;

import com.morchul.model.player.User;
import javafx.collections.ObservableList;

public class GameModel {

    public String gameName;
    public User gameMaster;
    public ObservableList<User> players;
    public String gameNumber;

    public GameModel(String gameName, User gameMaster, ObservableList<User> players, String gameNumber) {
        this.gameName = gameName;
        this.gameMaster = gameMaster;
        this.players = players;
        this.gameNumber = gameNumber;
    }
}
