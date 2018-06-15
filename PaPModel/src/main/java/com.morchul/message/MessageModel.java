package com.morchul.message;

import java.util.List;

public class MessageModel {

    public MessageType type;
    public String message;
    public List<Object> param;

    public MessageModel(MessageType type, String message, List<Object> param) {
        this.type = type;
        this.message = message;
        this.param = param;
    }
    public MessageModel(MessageType type, String message){
        this(type, message, null);
    }

    public enum MessageType {
        ERROR,
        LOGIN,
        LOGOUT,
        JOIN_GAME,
        CREATE_GAME,
        PLAYER_LEAF_GAME,
        START_GAME,
        INFORMATION,
        PLAYER_JOIN_GAME,
        CLOSE_GAME,
        GET_CHARACTER,
        READY,
        ALL_PLAYER_READY,
        CREATE_CHARACTER,
        LOAD_CHARACTER,
        SAVE_CHARACTER,
        CALL_BACK,

        //GAME

        MOVE_ITEM,
        ADD_STATUS,
        ADD_SKILL,
        DICE,
        ALL_TABLE,
        NEW_CREATURE,
        NEXT_ROUND,
        REMOVE_ITEM,
        REMOVE_STATUS,
        REMOVE_SKILL,
        CREATURE_VALUE_CHANGE,
        LOOT,
        KILL,
        FINISH_GAME,
        ADD_CHARACTERISTIC_POINT
    }
}
