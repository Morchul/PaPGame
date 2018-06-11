package com.morchul.game;

import com.morchul.PaPServer;
import com.morchul.connection.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameManager {

    private static List<GameWrapper> gameList = new ArrayList<>();

    /**
     * Try to get the Game per gameGroupNumber
     * @param gameNumber The Number of the Game you want to connect
     * @return if exist the Game else null
     */
    public static GameWrapper connectToGame(String gameNumber){
        PaPServer.log.info("Try connect to Game: " + gameNumber);
        GameWrapper wrapper = gameExist(gameNumber);
        if(wrapper != null)
            if(wrapper.getGame().isRunning())
                return null;
        return wrapper;
    }

    public static GameWrapper getGameWrapper(String gameNumber){
        for(GameWrapper wrapper : gameList){
            if(wrapper.getGame().getGameNumber().equals(gameNumber)){
                return wrapper;
            }
        }
        return null;
    }

    private static GameWrapper gameExist(String gameNumber){
        for(GameWrapper wrapper : gameList){
            if(wrapper.getGame().getGameNumber().equals(gameNumber))
                return wrapper;
        }
        return null;
    }

    public static String generateGameNumber(){
        String gameNumber;
        Random random = new Random();
        do {
            gameNumber = (random.nextInt(1000) + 1000) + "";
        } while(gameExist(gameNumber) != null);
        return gameNumber;
    }

    //Add remove GameGroups from GameManager
    public static GameWrapper createGame(Game game, Client gameManager){
        GameWrapper gw = new GameWrapper(game, gameManager);
        gameList.add(gw);
        PaPServer.log.info("Create Game : " + game.getGameNumber());
        return gw;
    }
    public static void removeGame(GameWrapper game){
        PaPServer.log.info("Destroy Game: " + game.getGame().getGameNumber());
        gameList.remove(game);
    }
}
