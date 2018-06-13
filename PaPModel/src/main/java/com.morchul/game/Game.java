package com.morchul.game;

import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.player.User;
import javafx.collections.ObservableList;

public interface Game {

    Creatures getCreature(Creatures c);
    Creatures getCreatureByUUID(String uuid);
    Creatures getCreatureByGameUUID(String gameUUID);

    User getUser(User user);
    User getGameMaster();

    void addNPC(Creatures creatures);
    void removeNPC(Creatures creatures);
    void removeNPC(String uuid);

    void addPlayer(User user);
    void removePlayer(User user);
    void removePlayer(String uuid);

    boolean IamTheGameMaster();
    boolean isGameMaster(User user);
    boolean isRunning();

    String getGameName();
    String getGameNumber();

    void start();
    void nextRound();
    int receiveCharacter();
    int getReceivedCharacter();

    ObservableList<User> getPlayers();
    ObservableList<Creatures> getNPCs();
}
