package com.morchul.game;

import com.morchul.PaPServer;
import com.morchul.connection.Client;
import com.morchul.connection.message.MessageModelCreator;
import com.morchul.message.MessageModel;

import java.util.ArrayList;
import java.util.List;

public class GameWrapper {


    private int ready = 0;

    private Game game;
    private List<Client> clients;

    public GameWrapper(Game game, Client gameMaster) {
        this.game = game;
        clients = new ArrayList<>();
        clients.add(gameMaster);
    }

    public Game getGame() {
        return game;
    }

    public void ready(){
        PaPServer.log.info("received ready message, now: " + (ready+1));
        if(++ready == clients.size()){
            PaPServer.log.info("send all player ready");
            sendToAll(MessageModelCreator.createAllPlayerReadyMessage());
        }
    }

    public synchronized void sendToAll(MessageModel message){
        for(Client c : clients){
            c.sendMessage(message);
        }
    }

    public void removeClientFromGame(Client c){
        clients.remove(c);
        game.removePlayer(c.getSelf().getUser());
    }

    public void destroyGame(){
        for(Client c : clients){
            c.getSelf().setGameWrapper(null);
        }
    }

    public void addClientToGame(Client c){
        game.addPlayer(c.getSelf().getUser());
        clients.add(c);
    }
}
