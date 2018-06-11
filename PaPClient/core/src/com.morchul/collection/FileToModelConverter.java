package com.morchul.collection;

import com.morchul.action.SimpleAction;
import com.morchul.model.Type;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.*;
import com.morchul.model.models.Object;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.morchul.helper.json.ClientJSONConverter.simpleStaticConverter;
import static com.morchul.json.JSONConverter.*;

public class FileToModelConverter {

    public static Anything create(JSONObject json, String path){
        Type type = new Type(json.getString(ANYTHING_TYPE_KEY));
        String imagePath = path + json.getString(ANYTHING_IMAGE_PATH_KEY);

        Anything anything;
        if(type.isType(Type.WEAPON)){
            anything = toWeapon(json, imagePath);
        } else if(type.isType(Type.STATUS)){
            anything = toStatus(json, imagePath);
        } else if(type.isType(Type.SKILLS)){
            anything = toSkill(json, imagePath);
        } else if(type.isType(Type.OBJECT)) {
            anything = toObject(json, imagePath);
        } else if(type.isType(Type.CREATURES)) {
            anything = toCreature(json, imagePath);
        } else if(type.isType(Type.ARMOR)){
            anything = toArmor(json, imagePath);
        } else {
            return null;
        }
        for(Value v: getValueList(json)){
            anything.getValues().add(v);
        }
        return anything;
    }

    private static Weapon toWeapon(JSONObject json, String imagePath){
        return new Weapon(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY),
                new SimpleAction(json.getString(ACTION_KEY)),
                Integer.parseInt(json.getString(WEAPON_VALUE_KEY))
        );
    }

    private static Status toStatus(JSONObject json, String imagePath){
        return new Status(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                new SimpleAction(json.getString(ACTION_KEY)),
                Integer.parseInt(json.getString(STATUS_DURATION_KEY))
        );
    }

    private static Skill toSkill(JSONObject json, String imagePath){
        return new Skill(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                new Type(json.getString(SKILL_NEED_WEAPON_KEY))
        );
    }

    private static Armor toArmor(JSONObject json, String imagePath){
        return new Armor(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY),
                new SimpleAction(json.getString(ACTION_KEY)),
                Integer.parseInt(json.getString(ARMOR_VALUE_KEY))
        );
    }

    private static Object toObject(JSONObject json, String imagePath){
        return new Object(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                json.getBoolean(OBJECTS_STACKABLE_KEY),
                json.getBoolean(OBJECTS_DROPABLE_KEY)
        );
    }

    private static Creatures toCreature(JSONObject json, String imagePath){
        return new Creatures(
                json.getString(ANYTHING_NAME_KEY),
                json.getString(ANYTHING_UUID_KEY),
                "",
                imagePath,
                json.getString(ANYTHING_DESCRIPTION_KEY),
                json.getString(ANYTHING_MASTER_DESCRIPTION_KEY),
                new Type(json.getString(ANYTHING_TYPE_KEY)),
                Integer.parseInt(json.getString(CREATURE_HP_KEY)),
                Integer.parseInt(json.getString(CREATURE_MAX_HP_KEY)),
                Integer.parseInt(json.getString(CREATURE_MP_KEY)),
                Integer.parseInt(json.getString(CREATURE_MAX_MP_KEY)),
                Integer.parseInt(json.getString(CREATURE_REACTION_KEY)),
                Integer.parseInt(json.getString(CREATURE_WILL_KEY)),
                Integer.parseInt(json.getString(CREATURE_STRENGTH_KEY)),
                Integer.parseInt(json.getString(CREATURE_RESISTANCE_KEY)),
                json.getBoolean(CREATURE_IMMORTAL_KEY)

        );
    }

    public static List<Value> getValueList(JSONObject json){
        List<Value> valueList = new ArrayList<>();
        for(java.lang.Object o : json.getJSONArray(ANYTHING_VALUES_KEY)){
            valueList.add(simpleStaticConverter.toValue(new JSONObject(o.toString())));
        }
        return valueList;
    }

    public static List<String> getInventoryUUIDList(JSONObject json){
        List<String> inventoryUUID = new ArrayList<>();
        for(java.lang.Object o : json.getJSONArray(CREATURE_INVENTORY_KEY)){
            inventoryUUID.add(o.toString());
        }
        return inventoryUUID;
    }

    public static List<String> getSkillUUIDList(JSONObject json) {
        List<String> skillsUUID = new ArrayList<>();
        for (java.lang.Object o : json.getJSONArray(CREATURE_SKILLS_KEY)) {
            skillsUUID.add(o.toString());
        }
        return skillsUUID;
    }

    public static List<String> getStatusUUIDList(JSONObject json){
        List<String> statusUUID = new ArrayList<>();
        for(java.lang.Object o : json.getJSONArray(CREATURE_STATUS_KEY)){
            statusUUID.add(o.toString());
        }
        return statusUUID;
    }
}
