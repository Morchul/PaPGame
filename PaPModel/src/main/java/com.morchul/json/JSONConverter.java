package com.morchul.json;

import com.morchul.game.Game;
import com.morchul.game.GameModel;
import com.morchul.message.MessageModel;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Objects;
import com.morchul.model.models.*;
import com.morchul.model.models.Object;
import com.morchul.model.player.User;
import org.json.JSONObject;

public interface JSONConverter {
    String VALUES_NAME_KEY = "valuesName";
    String VALUES_VALUE_KEY = "valuesValue";

    String TYPE_KEY = "type";
    String MESSAGE_KEY = "message";
    String PARAM_KEY = "param";

    String GAME_NUMBER_KEY = "gameNumber";
    String GAME_NAME_KEY = "gameName";
    String GAME_MASTER_KEY = "gameMaster";
    String GAME_PLAYER_KEY = "gamePlayer";

    String USER_NAME_KEY = "userName";
    String USER_UUID_KEY = "userUUID";
    String USER_CHARACTER_KEY = "userCharacter";

    String CREATURE_HP_KEY = "hp";
    String CREATURE_MAX_HP_KEY = "maxHp";
    String CREATURE_MP_KEY = "mp";
    String CREATURE_MAX_MP_KEY = "maxMp";
    String CREATURE_REACTION_KEY = "reaction";
    String CREATURE_WILL_KEY = "will";
    String CREATURE_STRENGTH_KEY = "strength";
    String CREATURE_RESISTANCE_KEY = "resistance";
    String CREATURE_IMMORTAL_KEY = "immortal";
    String CREATURE_INVENTORY_KEY = "inventory";
    String CREATURE_SKILLS_KEY = "skills";
    String CREATURE_STATUS_KEY = "status";

    String ANYTHING_UUID_KEY = "UUID";
    String ANYTHING_GAME_UUID_KEY = "gameUUID";
    String ANYTHING_NAME_KEY = "name";
    String ANYTHING_IMAGE_PATH_KEY = "imagePath";
    String ANYTHING_TYPE_KEY = "type";
    String ANYTHING_VALUES_KEY = "values";
    String ANYTHING_DESCRIPTION_KEY = "description";
    String ANYTHING_MASTER_DESCRIPTION_KEY = "masterDescription";

    String OBJECTS_STACKABLE_KEY = "stackable";
    String OBJECTS_DROPABLE_KEY = "dropable";

    String ARMOR_VALUE_KEY = "armorValue";

    String WEAPON_VALUE_KEY = "weaponValue";

    String SKILL_NEED_WEAPON_KEY = "needWeapon";

    String STATUS_DURATION_KEY = "duration";

    String ACTION_KEY = "action";

    JSONObject toJSON(Status s);
    JSONObject toJSON(MessageModel m);
    JSONObject toJSON(Skill s);
    JSONObject toJSON(Value v);
    JSONObject toJSON(Creatures c);
    JSONObject toJSON(Object o);
    JSONObject toJSON(Weapon w);
    JSONObject toJSON(Armor a);
    JSONObject toJSON(User u);
    JSONObject toJSON(Anything any);
    JSONObject toJSON(Objects o);
    JSONObject toJSON(Game g);
    JSONObject toPlayerWithCharacter(JSONObject json, Creatures c);

    Creatures toCreature(JSONObject json);
    Value toValue(JSONObject json);
    GameModel toGame(JSONObject json);
    User toPlayerWithCharacter(JSONObject json);
    MessageModel toMessageModel(JSONObject json);
    User toUser(JSONObject json);
    Object toObject(JSONObject json);
    Weapon toWeapon(JSONObject json);
    Armor toArmor(JSONObject json);
    Skill toSkill(JSONObject json);
    Status toStatus(JSONObject json);
    Objects toObjects(JSONObject json);
    Anything toAnything(JSONObject json);
}
