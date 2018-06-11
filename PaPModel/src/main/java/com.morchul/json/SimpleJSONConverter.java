package com.morchul.json;

import com.morchul.action.SimpleAction;
import com.morchul.game.Game;
import com.morchul.game.GameModel;
import com.morchul.message.MessageModel;
import com.morchul.model.SimpleValue;
import com.morchul.model.Type;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.models.*;
import com.morchul.model.models.Object;
import com.morchul.model.player.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SimpleJSONConverter implements JSONConverter {

    private JSONArrayHelper arrayHelper;

    public SimpleJSONConverter(JSONArrayHelper arrayHelper){
        this.arrayHelper = arrayHelper;
        this.arrayHelper.setJSONConverter(this);
    }

    @Override
    public JSONObject toJSON(Status s){
        return new JSONObject()
                .put(ANYTHING_UUID_KEY, s.getUUID())
                .put(ANYTHING_NAME_KEY, s.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, s.getImagePath())
                .put(ANYTHING_TYPE_KEY, s.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, s.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(s))
                .put(ANYTHING_DESCRIPTION_KEY, s.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, s.getMasterDescription())
                .put(ACTION_KEY, s.getAction().getActionText())
                .put(STATUS_DURATION_KEY, s.getDuration());
    }

    @Override
    public JSONObject toJSON(MessageModel m){
        return new JSONObject()
                .put(TYPE_KEY, m.type)
                .put(PARAM_KEY, m.param)
                .put(MESSAGE_KEY, m.message);
    }

    @Override
    public JSONObject toJSON(Skill s){

        return new JSONObject()
                .put(ANYTHING_UUID_KEY, s.getUUID())
                .put(ANYTHING_NAME_KEY, s.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, s.getImagePath())
                .put(ANYTHING_TYPE_KEY, s.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, s.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(s))
                .put(ANYTHING_DESCRIPTION_KEY, s.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, s.getMasterDescription())
                .put(SKILL_NEED_WEAPON_KEY, s.needWeapon().getIdentifier());
    }

    @Override
    public JSONObject toJSON(Value v){
        return new JSONObject()
                .put(VALUES_NAME_KEY, v.getName())
                .put(VALUES_VALUE_KEY, v.getValue());
    }

    @Override
    public JSONObject toJSON(Creatures c){
        return new JSONObject()
                .put(ANYTHING_UUID_KEY, c.getUUID())
                .put(ANYTHING_NAME_KEY, c.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, c.getImagePath())
                .put(ANYTHING_TYPE_KEY, c.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, c.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(c))
                .put(ANYTHING_DESCRIPTION_KEY, c.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, c.getMasterDescription())
                .put(CREATURE_HP_KEY, c.getHp())
                .put(CREATURE_MAX_HP_KEY, c.getMaxHp())
                .put(CREATURE_MP_KEY, c.getMp())
                .put(CREATURE_MAX_MP_KEY, c.getMaxMp())
                .put(CREATURE_REACTION_KEY, c.getReaction())
                .put(CREATURE_WILL_KEY, c.getWill())
                .put(CREATURE_STRENGTH_KEY, c.getStrength())
                .put(CREATURE_RESISTANCE_KEY, c.getResistance())
                .put(CREATURE_IMMORTAL_KEY, c.isImmortal())
                .put(CREATURE_INVENTORY_KEY, arrayHelper.getInventoryArray(c))
                .put(CREATURE_SKILLS_KEY, arrayHelper.getSkillArray(c))
                .put(CREATURE_STATUS_KEY, arrayHelper.getStatusArray(c));
    }

    @Override
    public JSONObject toJSON(Object o) {
        return new JSONObject()
                .put(ANYTHING_UUID_KEY, o.getUUID())
                .put(ANYTHING_NAME_KEY, o.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, o.getImagePath())
                .put(ANYTHING_TYPE_KEY, o.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, o.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(o))
                .put(ANYTHING_DESCRIPTION_KEY, o.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, o.getMasterDescription())
                .put(OBJECTS_STACKABLE_KEY, o.isStackable())
                .put(OBJECTS_DROPABLE_KEY, o.isDropable());
    }

    @Override
    public JSONObject toJSON(Weapon w) {
        return new JSONObject()
                .put(ANYTHING_UUID_KEY, w.getUUID())
                .put(ANYTHING_NAME_KEY, w.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, w.getImagePath())
                .put(ANYTHING_TYPE_KEY, w.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, w.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(w))
                .put(ANYTHING_DESCRIPTION_KEY, w.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, w.getMasterDescription())
                .put(OBJECTS_STACKABLE_KEY, w.isStackable())
                .put(OBJECTS_DROPABLE_KEY, w.isDropable())
                .put(ACTION_KEY, w.getPullOnAction().getActionText())
                .put(WEAPON_VALUE_KEY, w.getDamage());
    }

    @Override
    public JSONObject toJSON(Armor a) {
        return new JSONObject()
                .put(ANYTHING_UUID_KEY, a.getUUID())
                .put(ANYTHING_NAME_KEY, a.getName())
                .put(ANYTHING_IMAGE_PATH_KEY, a.getImagePath())
                .put(ANYTHING_TYPE_KEY, a.getType().getIdentifier())
                .put(ANYTHING_GAME_UUID_KEY, a.getGameUUID())
                .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(a))
                .put(ANYTHING_DESCRIPTION_KEY, a.getDescription())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, a.getMasterDescription())
                .put(OBJECTS_STACKABLE_KEY, a.isStackable())
                .put(OBJECTS_DROPABLE_KEY, a.isDropable())
                .put(ACTION_KEY, a.getPullOnAction().getActionText())
                .put(ARMOR_VALUE_KEY, a.getArmorValue());
    }

    @Override
    public JSONObject toJSON(Anything any) {
        Type type = any.getType();
        if(type.isType(Type.ARMOR)){
            return toJSON((Armor) any);
        } else if(type.isType(Type.CREATURES)){
            return toJSON((Creatures) any);
        } else if(type.isType(Type.OBJECT)){
            return toJSON((Object) any);
        } else if(type.isType(Type.SKILLS)){
            return toJSON((Skill) any);
        } else if(type.isType(Type.STATUS)){
            return toJSON((Status) any);
        } else if(type.isType(Type.WEAPON)){
            return toJSON((Weapon) any);
        } else {
            return new JSONObject()
                    .put(ANYTHING_UUID_KEY, any.getUUID())
                    .put(ANYTHING_NAME_KEY, any.getName())
                    .put(ANYTHING_IMAGE_PATH_KEY, any.getImagePath())
                    .put(ANYTHING_TYPE_KEY, any.getType().getIdentifier())
                    .put(ANYTHING_GAME_UUID_KEY, any.getGameUUID())
                    .put(ANYTHING_VALUES_KEY, arrayHelper.getValueArray(any))
                    .put(ANYTHING_DESCRIPTION_KEY, any.getDescription())
                    .put(ANYTHING_MASTER_DESCRIPTION_KEY, any.getMasterDescription());
        }
    }

    @Override
    public JSONObject toJSON(Objects o) {
        Type type = o.getType();
        if(type.isType(Type.ARMOR)) {
            return toJSON((Armor) o);
        } else if(type.isType(Type.OBJECT)){
            return toJSON((Object) o);
        } else if(type.isType(Type.WEAPON)){
            return toJSON((Weapon) o);
        } else {
            return null;
        }
    }

    //-----------------------------------------------------------
    //---------------------to Object-----------------------------
    //-----------------------------------------------------------

    @Override
    public Creatures toCreature(JSONObject json){
        Creatures c = new Creatures(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getInt(CREATURE_HP_KEY),
                json.getInt(CREATURE_MAX_HP_KEY),
                json.getInt(CREATURE_MP_KEY),
                json.getInt(CREATURE_MAX_MP_KEY),
                json.getInt(CREATURE_REACTION_KEY),
                json.getInt(CREATURE_WILL_KEY),
                json.getInt(CREATURE_STRENGTH_KEY),
                json.getInt(CREATURE_RESISTANCE_KEY),
                json.getBoolean(CREATURE_IMMORTAL_KEY)
        );

        arrayHelper.fillValueList(json, c);
        arrayHelper.fillInventoryList(json, c);
        arrayHelper.fillSkillList(json, c);
        arrayHelper.fillStatusList(json, c);

        return c;
    }

    @Override
    public JSONObject toJSON(User u){
        return new JSONObject()
                .put(USER_NAME_KEY, u.getUserName())
                .put(USER_UUID_KEY, u.getUserUUID());
    }

    @Override
    public Value toValue(JSONObject json){
        return new SimpleValue(
                json.getString(VALUES_NAME_KEY),
                json.getInt(VALUES_VALUE_KEY));
    }

    @Override
    public JSONObject toPlayerWithCharacter(JSONObject json, Creatures c){
        return json.put(USER_CHARACTER_KEY, toJSON(c));
    }

    @Override
    public JSONObject toJSON(Game g) {
        List<java.lang.Object> list = new ArrayList<>();
        for(User u : g.getPlayers()){
            list.add(toJSON(u));
        }

        return new JSONObject()
                .put(GAME_NUMBER_KEY, g.getGameNumber())
                .put(GAME_NAME_KEY, g.getGameName())
                .put(GAME_MASTER_KEY, toJSON(g.getGameMaster()))
                .put(GAME_PLAYER_KEY, list);
    }

    @Override
    public GameModel toGame(JSONObject json){
        ObservableList<User> players = FXCollections.observableArrayList();
        for(java.lang.Object o: json.getJSONArray(GAME_PLAYER_KEY)){
            players.add(toUser(new JSONObject(o.toString())));
        }

        return new GameModel(
                json.getString(GAME_NAME_KEY),
                toUser(json.getJSONObject(GAME_MASTER_KEY)),
                players,
                json.getString(GAME_NUMBER_KEY)
        );
    }

    @Override
    public User toPlayerWithCharacter(JSONObject json){
        User u = new User(json.getString(USER_NAME_KEY), json.getString(USER_UUID_KEY));
        u.setCharacter(toCreature(json.getJSONObject(USER_CHARACTER_KEY)));
        return u;
    }

    @Override
    public MessageModel toMessageModel(JSONObject json) {
        List<java.lang.Object> params = new ArrayList<>();
        for (java.lang.Object o : json.getJSONArray(PARAM_KEY)) {
            params.add(o);
        }
        return new MessageModel(MessageModel.MessageType.valueOf(json.get(TYPE_KEY).toString()),
                json.getString(MESSAGE_KEY),
                params);
    }

    @Override
    public User toUser(JSONObject json){
        return new User(json.getString(USER_NAME_KEY), json.getString(USER_UUID_KEY));
    }

    @Override
    public Object toObject(JSONObject json) {
        Object o = new Object(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY)
        );
        arrayHelper.fillValueList(json, o);
        return o;
    }

    @Override
    public Weapon toWeapon(JSONObject json) {
        Weapon w = new Weapon(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY),
                new SimpleAction(json.getString(ACTION_KEY)),
                json.getInt(WEAPON_VALUE_KEY)
        );
        arrayHelper.fillValueList(json, w);
        return w;
    }

    @Override
    public Armor toArmor(JSONObject json) {
        Armor a = new Armor(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY),
                new SimpleAction(json.getString(ACTION_KEY)),
                json.getInt(ARMOR_VALUE_KEY)
        );
        arrayHelper.fillValueList(json, a);
        return a;
    }

    @Override
    public Skill toSkill(JSONObject json) {
        Skill s = new Skill(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                new Type(json.getString(SKILL_NEED_WEAPON_KEY))
        );
        arrayHelper.fillValueList(json, s);
        return s;
    }

    @Override
    public Status toStatus(JSONObject json) {
        Status s = new Status(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                json.getString(ANYTHING_GAME_UUID_KEY),
                json.getString(ANYTHING_IMAGE_PATH_KEY),
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                new SimpleAction(json.getString(ACTION_KEY)),
                json.getInt(STATUS_DURATION_KEY)
        );
        arrayHelper.fillValueList(json, s);
        return s;
    }

    @Override
    public Objects toObjects(JSONObject json) {
        Type type = new Type(json.getString(ANYTHING_TYPE_KEY));
        if (type.isType(Type.OBJECT)) {
            return toObject(json);
        } else if(type.isType(Type.ARMOR)){
            return toArmor(json);
        } else if(type.isType(Type.WEAPON)){
            return toWeapon(json);
        } else {
            return null;
        }
    }

    @Override
    public Anything toAnything(JSONObject json) {
        Type type = new Type(json.getString(ANYTHING_TYPE_KEY));
        if(type.isType(Type.ARMOR)){
            return toArmor(json);
        } else if(type.isType(Type.CREATURES)){
            return toCreature(json);
        } else if(type.isType(Type.OBJECT)){
            return toObject(json);
        } else if(type.isType(Type.SKILLS)){
            return toSkill(json);
        } else if(type.isType(Type.STATUS)){
            return toStatus(json);
        } else if(type.isType(Type.WEAPON)){
            return toWeapon(json);
        } else {
            return null;
        }
    }
}
