package helper;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.morchul.json.JSONConverter.*;

public class JSONConverter {

    public static JSONObject toJSON(Model model){

        String imageType = model.imagePath.get();
        System.out.println(imageType);
        imageType = imageType.substring(imageType.length() - 4, imageType.length());
        String image = model.name.get() + imageType;

        JSONArray valueArray = new JSONArray();
        for(Value value: model.values){
            valueArray.put(toJSON(value));
        }

        JSONArray inventoryArray = new JSONArray();
        for(InventoryItem item: model.inventory){
            inventoryArray.put(item.itemUUID.get());
        }

        JSONArray skillArray = new JSONArray();
        for(SkillItem item: model.skills){
            skillArray.put(item.skillUUID.get());
        }

        JSONArray statusArray = new JSONArray();
        for(StatusItem item: model.status){
            statusArray.put(item.statusUUID.get());
        }

        return new JSONObject()
                .put(ANYTHING_UUID_KEY, model.uuid)
                .put(ANYTHING_NAME_KEY, model.name.get())
                .put(ANYTHING_IMAGE_PATH_KEY, image)
                .put(ANYTHING_TYPE_KEY, model.type.get())
                .put(ANYTHING_VALUES_KEY, valueArray)
                .put(ANYTHING_DESCRIPTION_KEY, model.description.get())
                .put(ANYTHING_MASTER_DESCRIPTION_KEY, model.masterDescription.get())
                .put(CREATURE_HP_KEY, model.hp.get())
                .put(CREATURE_MAX_HP_KEY, model.maxHp.get())
                .put(CREATURE_MP_KEY, model.mp.get())
                .put(CREATURE_MAX_MP_KEY, model.maxMp.get())
                .put(CREATURE_REACTION_KEY, model.reaction.get())
                .put(CREATURE_WILL_KEY, model.will.get())
                .put(CREATURE_STRENGTH_KEY, model.strength.get())
                .put(CREATURE_RESISTANCE_KEY, model.resistance.get())
                .put(CREATURE_INVENTORY_KEY, inventoryArray)
                .put(CREATURE_SKILLS_KEY, skillArray)
                .put(CREATURE_STATUS_KEY, statusArray)
                .put(OBJECTS_STACKABLE_KEY, model.stackable.get())
                .put(OBJECTS_DROPABLE_KEY, model.stackable.get())
                .put(CREATURE_IMMORTAL_KEY, model.immortal.get())
                .put(ACTION_KEY, model.action.get())
                .put(ARMOR_VALUE_KEY, model.armorValue.get())
                .put(WEAPON_VALUE_KEY, model.weaponValue.get())
                .put(SKILL_NEED_WEAPON_KEY, model.needWeapon.get())
                .put(STATUS_DURATION_KEY, model.duration.get());
    }

    public static JSONObject toJSON(Value value){
        return new JSONObject()
                .put(VALUES_NAME_KEY, value.name.get())
                .put(VALUES_VALUE_KEY, value.value.get());
    }

    public static Model toModel(String s){
        JSONObject json = new JSONObject(s);
        Model model = new Model(json.getString(ANYTHING_NAME_KEY));
        model.uuid = json.getString(ANYTHING_UUID_KEY);
        model.imagePath.set(json.getString(ANYTHING_IMAGE_PATH_KEY));
        model.type.set(json.getString(ANYTHING_TYPE_KEY));
        model.description.set(json.getString(ANYTHING_DESCRIPTION_KEY));
        model.masterDescription.set(json.getString(ANYTHING_MASTER_DESCRIPTION_KEY));
        model.hp.set(json.getString(CREATURE_HP_KEY));
        model.maxHp.set(json.getString(CREATURE_MAX_HP_KEY));
        model.mp.set(json.getString(CREATURE_MP_KEY));
        model.maxMp.set(json.getString(CREATURE_MAX_MP_KEY));
        model.reaction.set(json.getString(CREATURE_REACTION_KEY));
        model.will.set(json.getString(CREATURE_WILL_KEY));
        model.strength.set(json.getString(CREATURE_STRENGTH_KEY));
        model.resistance.set(json.getString(CREATURE_RESISTANCE_KEY));
        model.stackable.set(json.getBoolean(OBJECTS_STACKABLE_KEY));
        model.dropable.set(json.getBoolean(OBJECTS_DROPABLE_KEY));
        model.immortal.set(json.getBoolean(CREATURE_IMMORTAL_KEY));
        model.action.set(json.getString(ACTION_KEY));
        model.armorValue.set(json.getString(ARMOR_VALUE_KEY));
        model.weaponValue.set(json.getString(WEAPON_VALUE_KEY));
        model.needWeapon.set(json.getString(SKILL_NEED_WEAPON_KEY));
        model.duration.set(json.getString(STATUS_DURATION_KEY));

        for(Object o : json.getJSONArray(ANYTHING_VALUES_KEY)){
            model.values.add(toValue(new JSONObject(o.toString())));
        }

        for(Object o: json.getJSONArray(CREATURE_INVENTORY_KEY)){
            model.inventory.add(new InventoryItem(o.toString()));
        }

        for(Object o: json.getJSONArray(CREATURE_SKILLS_KEY)){
            model.skills.add(new SkillItem(o.toString()));
        }

        for(Object o: json.getJSONArray(CREATURE_STATUS_KEY)){
            model.status.add(new StatusItem(o.toString()));
        }
        return model;
    }

    public static Value toValue(JSONObject json){
        return new Value(json.getString(VALUES_NAME_KEY), json.getString(VALUES_VALUE_KEY));
    }
}
