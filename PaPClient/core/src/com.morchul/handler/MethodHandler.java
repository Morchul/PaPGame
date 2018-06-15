package com.morchul.handler;

import com.badlogic.gdx.Gdx;
import com.morchul.PaPHelper;
import com.morchul.connections.Server;
import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.inventory.ClientInventoryItem;
import com.morchul.inventory.InventoryItem;
import com.morchul.message.MessageModel;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import com.morchul.Self;
import com.morchul.model.player.User;
import com.morchul.ui.ScreenLoader;
import com.morchul.ui.character.CharacterValueView;
import com.morchul.ui.screens.AllTableScreen;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;

public class MethodHandler {

  private Server server;
  private Logger log = LoggerFactory.getLogger(PaPHelper.class);


  public MethodHandler(Server server) {
    this.server = server;
  }

  public void handle(MessageModel message){
      switch (message.type){
          case ERROR: errorEvent(message); break;
          case INFORMATION: informationEvent(message); break;

          case PLAYER_JOIN_GAME: playerJoinGameEvent(message); break;
          case PLAYER_LEAF_GAME: playerLeafGameEvent(message); break;
          case CLOSE_GAME: closeGameEvent(message); break;
          case START_GAME: startGameEvent(); break;
          case GET_CHARACTER: getCharacterEvent(message); break;
          case ALL_PLAYER_READY: allPlayerReadyEvent(); break;
          case MOVE_ITEM: moveItemEvent(message); break;
          case DICE: diceEvent(message); break;
          case ALL_TABLE: allTableEvent(message); break;
          case NEW_CREATURE: newCreatureEvent(message); break;
          case NEXT_ROUND: nextRoundEvent(); break;
          case ADD_SKILL: addSkillEvent(message); break;
          case ADD_STATUS: addStatusEvent(message); break;
          case REMOVE_ITEM: removeItemEvent(message); break;
          case REMOVE_STATUS: removeStatusEvent(message); break;
          case REMOVE_SKILL: removeSkillEvent(message); break;
          case CREATURE_VALUE_CHANGE: creatureValueChangeEvent(message); break;
          case LOOT: lootEvent(message); break;
          case KILL: killEvent(message); break;
          case FINISH_GAME: finishGameEvent(); break;
          case CALL_BACK: callBackEvent(); break;
          case ADD_CHARACTERISTIC_POINT: addCharacteristicPointEvent(message); break;
          case VALUE_VALUE_CHANGE: valueValueChangeEvent(message); break;
          default: log.error("NOT IMPLEMENTED: " + message.type); break;
      }
  }

  private void valueValueChangeEvent(MessageModel message){
      Creatures who = Self.game.getCreatureByGameUUID((String)message.param.get(1));
      if(who != null) {
          Objects o = who.getInventory().getInventoryItemByGameUUID((String) message.param.get(0)).getItem();
          Value v = o.getValueByName((String)message.param.get(2));
          if(v != null)
              v.setValue((int)message.param.get(3));
      }
  }

  private void addCharacteristicPointEvent(MessageModel message){
      if((message.param.get(0)).equals(Self.user.getCharacter().getGameUUID())){
          CharacterValueView.addPoints((int)message.param.get(1));
      } else {
          log.error("Incorrect forwarding!!");
      }
  }

  private void callBackEvent(){
      //Empty method
  }

  private void finishGameEvent(){
      if(!Self.game.IamTheGameMaster()) {
          StaticServerInterface.sendMessage(MessageModelCreator.createSaveCharacterMessage());
          StaticServerInterface.sendMessage(MessageModelCreator.createLeafGameMessage());
          StaticServerInterface.read();
          Self.leafGame();
      }
  }

  private void killEvent(MessageModel message){
      Creatures who = Self.game.getCreatureByGameUUID(message.message);
      Self.game.removeNPCDirectly(who);
      if(Self.user.getCharacter().getGameUUID().equals(message.message)){
          StaticServerInterface.sendMessage(MessageModelCreator.createLeafGameMessage());
          StaticServerInterface.read();
          Self.leafGame("You were killed by GameMaster");
      }
  }

  private void lootEvent(MessageModel message){
      System.out.println("LOOT EVENT");
      Creatures from = Self.game.getCreatureByGameUUID((String)message.param.get(0));
      Creatures to = Self.game.getCreatureByGameUUID((String)message.param.get(1));
      if(from != null && to != null) {
          for (InventoryItem item : from.getInventory().getInventoryList()) {
              to.getInventory().addItemToInventory(item);
          }
          from.getInventory().clear();
      }
  }

  private void creatureValueChangeEvent(MessageModel message){
      Creatures who = Self.game.getCreatureByGameUUID((String)message.param.get(2));
      who.setValue((String)message.param.get(1), (int)message.param.get(0));
  }

    private void removeStatusEvent(MessageModel message){
        Creatures who = Self.game.getCreatureByGameUUID((String)message.param.get(1));
        if(who != null) {
            Status s = who.getStatusByGameUUID((String) message.param.get(0));
            if (s != null)
                who.removeStatus(s);
        }
    }

    private void removeSkillEvent(MessageModel message){
        Creatures who = Self.game.getCreatureByGameUUID((String)message.param.get(1));
        if(who != null){
            Skill s = who.getSkillByGameUUID((String)message.param.get(0));
            if(s != null)
                who.removeSkill(s);
        }
    }

    private void removeItemEvent(MessageModel message){
      Creatures who = Self.game.getCreatureByGameUUID((String)message.param.get(1));
      ClientInventoryItem item = new ClientInventoryItem(simpleStaticConverter.toObjects((JSONObject)message.param.get(0)));
      if(who != null)
        who.getInventory().removeItemTotalFromInventory(item);
    }

    private void addStatusEvent(MessageModel message){
        Creatures to = Self.game.getCreatureByGameUUID((String)message.param.get(1));
        Status s = simpleStaticConverter.toStatus((JSONObject)message.param.get(0));
        if(to != null)
            to.addStatus(s);
    }

    private void addSkillEvent(MessageModel message){
        Creatures to = Self.game.getCreatureByGameUUID((String)message.param.get(1));
        Skill s = simpleStaticConverter.toSkill((JSONObject)message.param.get(0));
        if(to != null)
            to.addSkill(s);
    }

  private void nextRoundEvent(){
      Self.game.nextRound();
  }

  private void newCreatureEvent(MessageModel message){
      Creatures c = simpleStaticConverter.toCreature(new JSONObject(message.message));
      log.info("Add Creature: " + c.getGameUUID());
      Self.game.addNPC(c);
  }

  private void allTableEvent(MessageModel message){
      Gdx.app.postRunnable(()-> AllTableScreen.getInstance().addAnything(simpleStaticConverter.toAnything(new JSONObject(message.message))));
  }

  private void diceEvent(MessageModel message){
      Creatures c = Self.game.getCreatureByGameUUID((String)message.param.get(0));
      ScreenLoader.setStatus(c.getName() + " dices: " + message.param.get(1) + " ("+ message.param.get(2) +")");
  }

  private void moveItemEvent(MessageModel message){
      Creatures from = Self.game.getCreatureByGameUUID((String)message.param.get(0));
      Creatures to = Self.game.getCreatureByGameUUID((String)message.param.get(1));
      Objects o = simpleStaticConverter.toObjects(new JSONObject(message.message));
      if(to != null)
          to.getInventory().addItemToInventory(new ClientInventoryItem(o));
      if(from != null)
          from.getInventory().removeItemFromInventory(new ClientInventoryItem(o));
  }

  private void allPlayerReadyEvent(){
      log.info("AllPlayerReady");
      log.info("START GAME...");
      Gdx.app.postRunnable(() -> {
          if(Self.game.IamTheGameMaster()) {
              ScreenLoader.changeScreen(ScreenLoader.Screens.GAME_MASTER);
          } else {
              ScreenLoader.changeScreen(ScreenLoader.Screens.GAME);
          }
      });
  }

  private void getCharacterEvent(MessageModel message){
      User user = simpleStaticConverter.toPlayerWithCharacter(new JSONObject(message.message));
      log.info("getCreatureByUUID from: " + user.getUserUUID());
      if(!user.getUserUUID().equals(Self.user.getUserUUID())) {
        Self.game.getUser(user).setCharacter(user.getCharacter());
      } else {
          Self.game.getUser(user).setCharacter(Self.user.getCharacter());
      }
      if(Self.game.receiveCharacter() == Self.game.getPlayers().size()){
          Self.loadCollection();
          server.sendMessage(MessageModelCreator.createReadyMessage());
      }
  }

  private void startGameEvent(){
      ScreenLoader.setStatus("Game Starting...");
      Self.game.start();
      Creatures c = Self.user.getCharacter();
      c.setGameUUID(UUID.randomUUID().toString());
      server.sendMessage(MessageModelCreator.createGetCharacterMessage(c));
  }

  private void closeGameEvent(MessageModel message){
      log.info(message.message);
      Self.leafGame();
  }

  private void playerJoinGameEvent(MessageModel message){
      User joinedPlayer = simpleStaticConverter.toUser(new JSONObject(message.message));
      Self.game.addPlayer(joinedPlayer);
  }

    private void playerLeafGameEvent(MessageModel message){
        User leafedPlayer = simpleStaticConverter.toUser(new JSONObject(message.message));
        Self.game.removePlayer(leafedPlayer);
    }

  private void informationEvent(MessageModel message) { log.info(message.message); }
  private void errorEvent(MessageModel message){ log.error(message.message); }

}
