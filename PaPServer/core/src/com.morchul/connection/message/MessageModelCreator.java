package com.morchul.connection.message;

import com.morchul.game.Game;
import com.morchul.json.JSONConverter;
import com.morchul.message.MessageModel;
import com.morchul.model.player.User;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MessageModelCreator {

    private static JSONConverter converter;

    public MessageModelCreator(JSONConverter con){
        converter = con;
    }

    public static MessageModel createAllPlayerReadyMessage(){
        return new MessageModel(MessageModel.MessageType.ALL_PLAYER_READY, "");
    }

    public static MessageModel createSendCharacterMessage(User user){
        return new MessageModel(MessageModel.MessageType.GET_CHARACTER, converter.toPlayerWithCharacter(converter.toJSON(user), user.getCharacter()).toString());
    }

    public static MessageModel createPositiveLoginMessage(User user){
        return new MessageModel(MessageModel.MessageType.LOGIN, converter.toJSON(user).toString());
    }

    public static MessageModel createPositiveJoinMessage(Game game){
        List<Object> param = new ArrayList<>();
        param.add(false);
        return new MessageModel(MessageModel.MessageType.JOIN_GAME, converter.toJSON(game).toString(), param);
    }

    public static MessageModel createCreateGameMessage(Game game){
        List<Object> param = new ArrayList<>();
        param.add(true);
        return new MessageModel(MessageModel.MessageType.CREATE_GAME, converter.toJSON(game).toString(), param);
    }

    public static MessageModel createNegativeJoinMessage(){
        return new MessageModel(MessageModel.MessageType.JOIN_GAME, "NOK");
    }

    public static MessageModel createNegativeLoginMessage(){
        return new MessageModel(MessageModel.MessageType.LOGIN, "NOK");
    }

    public static MessageModel createInformationMessage(String information){
        return new MessageModel(MessageModel.MessageType.INFORMATION, information);
    }

    public static MessageModel createPlayerJoinGameMessage(User user){
        return new MessageModel(MessageModel.MessageType.PLAYER_JOIN_GAME, converter.toJSON(user).toString());
    }

    public static MessageModel createPlayerLeafGameMessage(User user){
        return new MessageModel(MessageModel.MessageType.PLAYER_LEAF_GAME, converter.toJSON(user).toString());
    }

    public static MessageModel createCloseGameMessage(){
        return new MessageModel(MessageModel.MessageType.CLOSE_GAME, "Game master has close the game");
    }

    public static MessageModel createLoadCharacterMessage(List<JSONObject> characters){
        return new MessageModel(MessageModel.MessageType.LOAD_CHARACTER,"", new ArrayList<>(characters));
    }
}
