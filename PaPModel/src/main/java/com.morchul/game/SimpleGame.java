package com.morchul.game;

import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Status;
import com.morchul.model.player.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public abstract class SimpleGame implements Game {

    protected String gameName;
    protected String gameNumber;
    protected User gameMaster;
    protected int receivedCharacter = 0;
    protected Logger log = LoggerFactory.getLogger(SimpleGame.class);

    protected boolean running = false;

    protected ObservableList<User> player;
    protected ObservableList<Creatures> npc;

    public SimpleGame(String name, User gameMaster, ObservableList<User> player, String gameNumber) {
        this.gameName = name;
        this.gameMaster = gameMaster;
        this.player = player;
        this.gameNumber = gameNumber;
        npc = FXCollections.observableArrayList();
        log.info("Create Game with name: " + gameName + " and number: " + gameNumber);
    }

    @Override
    public Creatures getCreature(Creatures creatures){
        Creatures c = getCreatureByUUID(creatures.getUUID());
        if(c != null)
            return c;
        return creatures;
    }

    @Override
    public Creatures getCreatureByUUID(String uuid) {
        for(User user: player) {
            if (uuid.equals(user.getCharacter().getUUID())) {
                return user.getCharacter();
            }
        }
        for(Creatures creatures: npc){
            if (uuid.equals(creatures.getUUID())) {
                return creatures;
            }
        }
        log.info("Can't find Creature with uuid: " + uuid);
        return null;
    }

    @Override
    public Creatures getCreatureByGameUUID(String gameUUID) {
        for(User user: player) {
            if (gameUUID.equals(user.getCharacter().getGameUUID())) {
                return user.getCharacter();
            }
        }
        for(Creatures creatures: npc){
            if (gameUUID.equals(creatures.getGameUUID())) {
                return creatures;
            }
        }
        log.info("Can't find Creature with gameUUID: " + gameUUID);
        return null;
    }

    @Override
    public void start(){
        running = true;
    }

    @Override
    public boolean isRunning(){
        return running;
    }

    @Override
    public void addNPC(Creatures creatures) {
        npc.add(creatures);
    }

    @Override
    public void removeNPC(Creatures creatures){
        removeNPCByGameUUID(creatures.getGameUUID());
    }

    @Override
    public void removeNPCDirectly(Creatures creature){
        npc.remove(creature);
    }

    @Override
    public void removeNPCByGameUUID(String gameUUID) {
        log.info("Remove creature from game: " + gameUUID);
        for(Creatures c : npc){
            if(c.getGameUUID().equals(gameUUID)) {
                npc.remove(c);
                return;
            }
        }
    }

    @Override
    public void addPlayer(User user){
        player.add(user);
    }

    @Override
    public void removePlayer(String uuid) {
        log.info("Remove player from game: " + uuid);
        for(User u: player){
            if(u.getUserUUID().equals(uuid)) {
                player.remove(u);
                return;
            }
        }
    }

    public synchronized void nextRound(){
        for(User u : player){
            List<Status> toRemove = new ArrayList<>();
            for(Status s: u.getCharacter().getStatus()){
                if(!s.action(u.getCharacter())){
                    toRemove.add(s);
                }
            }
            remove(u.getCharacter(), toRemove);
        }
        for(Creatures c: npc){
            List<Status> toRemove = new ArrayList<>();
            for(Status s : c.getStatus()){
                if(!s.action(c)){
                    toRemove.add(s);
                }
            }
            remove(c, toRemove);
        }
    }

    private void remove(Creatures c, List<Status> s){
        for(Status status: s){
            c.removeStatus(status);
        }

    }

    @Override
    public boolean isGameMaster(User user) {
        return gameMaster.getUserUUID().equals(user.getUserUUID());
    }

    @Override
    public String getGameName() {
        return gameName;
    }

    @Override
    public String getGameNumber() {
        return gameNumber;
    }

    @Override
    public User getGameMaster() {
        return gameMaster;
    }

    @Override
    public User getUser(User user) {
        for(User u: player){
            if(u.getUserUUID().equals(user.getUserUUID()))
                return u;
        }
        return null;
    }

    @Override
    public int receiveCharacter() {
        return ++receivedCharacter;
    }

    @Override
    public int getReceivedCharacter() {
        return receivedCharacter;
    }

    @Override
    public ObservableList<User> getPlayers() {
        return player;
    }
    @Override
    public ObservableList<Creatures> getNPCs() {return npc;}
}
