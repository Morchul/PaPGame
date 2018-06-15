package com.morchul.json;

import com.morchul.model.ServerImpCreatures;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;
import org.json.JSONObject;

public class ServerJSONConverter extends SimpleJSONConverter {

    public ServerJSONConverter(JSONArrayHelper arrayHelper) {
        super(arrayHelper);
    }

    @Override
    public Creatures toCreature(JSONObject json){
        Creatures c = new ServerImpCreatures(
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
}
