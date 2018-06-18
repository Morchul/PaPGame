package com.morchul.handler;


import com.morchul.PaPServer;
import com.morchul.action.Action;
import com.morchul.action.SimpleAction;
import com.morchul.connection.Client;
import com.morchul.connection.message.MessageModelCreator;
import com.morchul.game.Game;
import com.morchul.game.GameManager;
import com.morchul.game.GameWrapper;
import com.morchul.game.SimpleServerGame;
import com.morchul.inventory.InventoryItem;
import com.morchul.inventory.ServerInventoryItem;
import com.morchul.json.JSONConverter;
import com.morchul.message.MessageModel;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import com.morchul.model.player.User;
import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class MethodHandler {

  private Client client;
  private JSONConverter converter;

  public MethodHandler(Client client, JSONConverter converter) {
    this.client = client;
    this.converter = converter;
  }

  public void handle(MessageModel message){
    switch (message.type){
        case ERROR: break;
        case LOGIN: loginEvent(message); break;
        case LOGOUT: logoutEvent(); break;
        case JOIN_GAME: joinGameEvent(message); break;
        case CREATE_GAME: createGameEvent(message); break;
        case PLAYER_LEAF_GAME: playerLeafGameEvent(); break;
        case START_GAME: startGameEvent(message); break;
        case GET_CHARACTER: getCharacterEvent(message); break;
        case READY: readyEvent(); break;
        case MOVE_ITEM: moveItemEvent(message); break;
        case DICE: sendFurther(message); break;
        case ALL_TABLE: sendFurther(message); break;
        case NEW_CREATURE: newCreatureEvent(message); break;
        case NEXT_ROUND: nextRoundEvent(message); break;
        case ADD_STATUS: addStatusEvent(message); break;
        case ADD_SKILL: addSkillEvent(message); break;
        case REMOVE_ITEM: removeItemEvent(message); break;
        case REMOVE_STATUS: removeStatusEvent(message); break;
        case REMOVE_SKILL: removeSkillEvent(message); break;
        case CREATURE_VALUE_CHANGE: creatureValueChangeEvent(message); break;
        case LOOT: lootEvent(message); break;
        case KILL: killEvent(message); break;
        case CREATE_CHARACTER: createCharacterEvent(message); break;
        case LOAD_CHARACTER: loadCharacterEvent(); break;
        case FINISH_GAME: finishGameEvent(message); break;
        case SAVE_CHARACTER: saveCharacterEvent(); break;
        case CALL_BACK: callBackEvent(message); break;
        case ADD_CHARACTERISTIC_POINT: addCharacteristicPointEvent(message); break;
        case VALUE_VALUE_CHANGE: valueValueChangedEvent(message); break;
        case ACTION: actionEvent(message); break;
        default: PaPServer.log.error("NOT IMPLEMENTED: " + message.type);
    }
  }

  private void actionEvent(MessageModel message){
      Creatures target = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      if(target != null) {
          InventoryItem item = target.getInventory().getInventoryItemByGameUUID((String) message.param.get(0));
          Objects source;
          Action action = new SimpleAction(message.message);
          if(item == null)
              source = converter.toObjects((JSONObject)message.param.get(2));
          else
              source = item.getItem();
          action.action(source, target);
          sendFurther(message);
      }
  }

  private void valueValueChangedEvent(MessageModel message){
      Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      if(who != null) {
          Objects o = who.getInventory().getInventoryItemByGameUUID((String) message.param.get(0)).getItem();
          Value v = o.getValueByName((String) message.param.get(2));
          if (v != null) {
              v.setValue((int) message.param.get(3));
              sendFurther(message);
          }
      }
  }

  private void addCharacteristicPointEvent(MessageModel message){
      Client c = client.getSelf().getGameWrapper().getClientByCharacterGameUUID((String)message.param.get(0));
      if(c != null) c.sendMessage(message);
  }

  private void callBackEvent(MessageModel message){
      client.sendMessage(message);
  }

  private void saveCharacterEvent(){
      Creatures character = client.getSelf().getUser().getCharacter();
      //Set Values back
      character.setGameUUID("");
      character.clearStatus();
      character.setHp(character.getMaxHp());
      character.setMp(character.getMaxMp());
      //--------------
      PaPServer.database.saveCharacter(converter.toJSON(character), client.getSelf().getUser().getUserName());
  }

  private void finishGameEvent(MessageModel message){
      sendFurther(message);
  }

  private void loadCharacterEvent(){
      List<JSONObject> characters = PaPServer.database.loadCharacters(client.getSelf().getUser().getUserName());
      client.sendMessage(MessageModelCreator.createLoadCharacterMessage(characters));
  }

  private void createCharacterEvent(MessageModel message){
      PaPServer.database.createCharacter(new JSONObject(message.message),client.getSelf().getUser().getUserName());
  }

  private void killEvent(MessageModel message){
      Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID(message.message);
      if(who != null)
        client.getSelf().getGameWrapper().getGame().removeNPCDirectly(who);
      sendFurther(message);
  }

  private void lootEvent(MessageModel message){
      Creatures from = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(0));
      Creatures to = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      if(from != null & to != null) {
          for (InventoryItem item : from.getInventory().getInventoryList()) {
              to.getInventory().addItemToInventory(item);
          }
          from.getInventory().clear();
      }
      sendFurther(message);
  }

  private void creatureValueChangeEvent(MessageModel message){
        Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(2));
        if(who != null){
            who.setValue((String)message.param.get(1), (int)message.param.get(0));
        }
        sendFurther(message);
  }

  private void removeStatusEvent(MessageModel message){
        Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
        if(who != null) {
            Status s = who.getStatusByGameUUID((String) message.param.get(0));
            if (s != null)
                who.removeStatus(s);
        }
      sendFurther(message);
  }

  private void removeSkillEvent(MessageModel message){
      Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      if(who != null){
          Skill s = who.getSkillByGameUUID((String)message.param.get(0));
          if(s != null)
              who.removeSkill(s);
      }
      sendFurther(message);
  }

  private void removeItemEvent(MessageModel message){
      Creatures who = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      ServerInventoryItem item = new ServerInventoryItem(converter.toObjects((JSONObject)message.param.get(0)));
      if(who != null)
          who.getInventory().removeItemTotalFromInventory(item);
      sendFurther(message);
  }

  private void addStatusEvent(MessageModel message){
      Creatures to = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      Status s = converter.toStatus((JSONObject)message.param.get(0));
      if(to != null)
          to.addStatus(s);
      sendFurther(message);
  }

  private void addSkillEvent(MessageModel message){
      Creatures to = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      Skill s = converter.toSkill((JSONObject)message.param.get(0));
      if(to != null)
          to.addSkill(s);
      sendFurther(message);
  }

  private void nextRoundEvent(MessageModel message){
      sendFurther(message);
      client.getSelf().getGameWrapper().getGame().nextRound();
  }

  private void newCreatureEvent(MessageModel message){
      Creatures c = converter.toCreature(new JSONObject(message.message));
      client.getSelf().getGameWrapper().getGame().addNPC(c);
      sendFurther(message);
  }

  private void moveItemEvent(MessageModel message){
      Creatures from = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(0));
      Creatures to = client.getSelf().getGameWrapper().getGame().getCreatureByGameUUID((String)message.param.get(1));
      Objects o = converter.toObjects(new JSONObject(message.message));
      if(from != null)
          from.getInventory().removeItemFromInventory(new ServerInventoryItem(o));
      if(to != null)
          to.getInventory().addItemToInventory(new ServerInventoryItem(o));
      sendFurther(message);
  }

  private void readyEvent(){
      client.getSelf().getGameWrapper().ready();
  }

  private void getCharacterEvent(MessageModel message){
      PaPServer.log.info("received Creature from: " + client.getID());
      Creatures c = converter.toCreature(new JSONObject(message.message));
      client.getSelf().getUser().setCharacter(c);
      PaPServer.log.info("send Creature from: " + client.getID() + " to all players");
      sendFurther(MessageModelCreator.createSendCharacterMessage(client.getSelf().getUser()));
  }

  private void startGameEvent(MessageModel message){
      PaPServer.log.info("Start game: " + client.getSelf().getGameWrapper().getGame().getGameNumber());
      client.getSelf().getGameWrapper().getGame().start();
      sendFurther(message);
  }

  private void playerLeafGameEvent(){
      PaPServer.log.info("Client: " + client.getID() + " leave game: " + client.getSelf().getGameWrapper().getGame().getGameNumber());
      client.getSelf().getGameWrapper().removeClientFromGame(client);
      client.getSelf().setGameWrapper(null);
      client.sendMessage(MessageModelCreator.createInformationMessage("Successful leaf game"));
  }

  private void createGameEvent(MessageModel message){
      Game game = new SimpleServerGame(client.getSelf().getUser(), message.message);
      client.getSelf().setGameWrapper(GameManager.createGame(game, client));
      client.sendMessage(MessageModelCreator.createCreateGameMessage(game));
  }

  private void joinGameEvent(MessageModel message){
      String gameNumber = message.message;

      //Try connect to game
      if(connectToGame(gameNumber) == null){
          PaPServer.log.info("Can't find a GAME with number: " + gameNumber);
          client.sendMessage(MessageModelCreator.createNegativeJoinMessage());
      } else {
          PaPServer.log.info("Client: " + client.getID() + " joint game: " + client.getSelf().getGameWrapper().getGame().getGameNumber());
          client.sendMessage(MessageModelCreator.createPositiveJoinMessage(client.getSelf().getGameWrapper().getGame()));
      }
  }

  private void logoutEvent(){
    client.logout();
  }

  private void loginEvent(MessageModel message){
    String username = message.param.get(0).toString();
    String password = message.param.get(1).toString();
    if(PaPServer.database.login(username, password)){
        PaPServer.log.info("Successfully logged in with USERNAME: " + username + " and PASSWORD: " + password);
        client.login(new User(username, UUID.randomUUID().toString()));
        client.sendMessage(MessageModelCreator.createPositiveLoginMessage(client.getSelf().getUser()));
    } else {
        PaPServer.log.info("Unsuccessfully try to login with USERNAME: " + username + " and PASSWORD: " + password);
        client.sendMessage(MessageModelCreator.createNegativeLoginMessage());
    }
  }

  private GameWrapper connectToGame(String gameNumber){
      GameWrapper gameWrapper = GameManager.connectToGame(gameNumber);
      if(gameWrapper != null){
          client.getSelf().setGameWrapper(gameWrapper);
          client.getSelf().getGameWrapper().addClientToGame(client);
          return gameWrapper;
      }
      return null;
  }

  private void sendFurther(MessageModel message){
      client.getSelf().getGameWrapper().sendToAll(message);
  }
}
