package com.morchul.connections.message;

import com.morchul.PaPHelper;
import com.morchul.inventory.InventoryItem;
import com.morchul.message.MessageModel;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.abstractmodels.Creatures;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;

public class MessageModelCreator {

    private static Logger log = LoggerFactory.getLogger(PaPHelper.class);

    public static MessageModel createReadyMessage(){
        log.info("ReadyMessage");
        return new MessageModel(MessageModel.MessageType.READY, "");
    }

    public static MessageModel createLoginMessage(String username, String password){
        log.info("LoginMessage");
        List<Object> userPass = new ArrayList<>();
        userPass.add(username);
        userPass.add(password);
        return new MessageModel(MessageModel.MessageType.LOGIN, "", userPass);
    }

    public static MessageModel createLogoutMessage() {
        log.info("LogoutMessage");
        return new MessageModel(MessageModel.MessageType.LOGOUT, "");
    }

    public static MessageModel createJoinGameMessage(String gameNumber){
        log.info("JoinGameMessage");
        return new MessageModel(MessageModel.MessageType.JOIN_GAME, gameNumber);
    }

    public static MessageModel createErrorMessage(String error){
        log.info("ErrorMessage");
        return new MessageModel(MessageModel.MessageType.ERROR, error);
    }

    public static MessageModel createCreateGameMessage(String gameName){
        log.info("CreateGameMessage");
        return new MessageModel(MessageModel.MessageType.CREATE_GAME, gameName);
    }

    public static MessageModel createLeafGameMessage(){
        log.info("LeafGameMessage");
        return new MessageModel(MessageModel.MessageType.PLAYER_LEAF_GAME, "");
    }

    public static MessageModel createStartGameMessage(){
        log.info("StartGameMessage");
        return new MessageModel(MessageModel.MessageType.START_GAME, "");
    }

    public static MessageModel createGetCharacterMessage(Creatures character){
        log.info("GetCharacterMessage");
        return new MessageModel(MessageModel.MessageType.GET_CHARACTER, simpleStaticConverter.toJSON(character).toString());
    }

    public static MessageModel createMoveItemToCreatureMessage(Objects any, Creatures from, Creatures to){
        log.info("Move Item: " + any.getGameUUID() + ", from Creature: " + from.getGameUUID() + " to: " + to.getGameUUID());
        List<Object> param = new ArrayList<>();
        param.add(from.getGameUUID());
        param.add(to.getGameUUID());
        return new MessageModel(MessageModel.MessageType.MOVE_ITEM, simpleStaticConverter.toJSON(any).toString(), param);
    }

    public static MessageModel createDiceMessage(int dice, int maxDice, Creatures who){
        log.info("Dice " + dice);
        List<Object> param = new ArrayList<>();
        param.add(who.getGameUUID());
        param.add(dice);
        param.add(maxDice);
        return new MessageModel(MessageModel.MessageType.DICE, "", param);
    }

    public static MessageModel createAllTableMessage(Anything any){
        return new MessageModel(MessageModel.MessageType.ALL_TABLE, simpleStaticConverter.toJSON(any).toString());
    }

    public static MessageModel createAddCreatureMessage(Creatures creatures){
        return new MessageModel(MessageModel.MessageType.NEW_CREATURE, simpleStaticConverter.toJSON(creatures).toString());
    }

    public static MessageModel createNextRoundMessage(){
        return new MessageModel(MessageModel.MessageType.NEXT_ROUND,"");
    }

    public static MessageModel createAddStatusMessage(Status s, Creatures to){
        List<Object> param = new ArrayList<>();
        param.add(simpleStaticConverter.toJSON(s));
        param.add(to.getGameUUID());
        return new MessageModel(MessageModel.MessageType.ADD_STATUS,"", param);
    }

    public static MessageModel createAddSkillMessage(Skill s, Creatures to){
        List<Object> param = new ArrayList<>();
        param.add(simpleStaticConverter.toJSON(s));
        param.add(to.getGameUUID());
        return new MessageModel(MessageModel.MessageType.ADD_SKILL,"", param);
    }

    public static MessageModel createRemoveItemTotalFromInventory(InventoryItem item, Creatures who){
        List<Object> param = new ArrayList<>();
        param.add(simpleStaticConverter.toJSON(item.getItem()));
        param.add(who.getGameUUID());
        return new MessageModel(MessageModel.MessageType.REMOVE_ITEM,"", param);
    }

    public static MessageModel createRemoveStatus(Status s, Creatures who){
        List<Object> param = new ArrayList<>();
        param.add(s.getGameUUID());
        param.add(who.getGameUUID());
        return new MessageModel(MessageModel.MessageType.REMOVE_STATUS,"", param);
    }

    public static MessageModel createRemoveSkill(Skill s, Creatures who){
        List<Object> param = new ArrayList<>();
        param.add(s.getGameUUID());
        param.add(who.getGameUUID());
        return new MessageModel(MessageModel.MessageType.REMOVE_SKILL,"", param);
    }

    public static MessageModel createValueChangedMessage(int value, Creatures who, String valueName){
        List<Object> param = new ArrayList<>();
        param.add(value);
        param.add(valueName);
        param.add(who.getGameUUID());
        return new MessageModel(MessageModel.MessageType.CREATURE_VALUE_CHANGE,"", param);
    }
}
