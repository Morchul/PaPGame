package com.morchul.json;

import com.morchul.inventory.InventoryItem;
import com.morchul.inventory.ServerInventoryItem;
import com.morchul.model.Type;
import com.morchul.model.Value;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.*;
import com.morchul.model.models.Object;
import org.json.JSONArray;
import org.json.JSONObject;

import static com.morchul.json.JSONConverter.*;

public class ServerJSONArrayHelper implements JSONArrayHelper{

    private JSONConverter converter;

    public ServerJSONArrayHelper(){ }

    public JSONArray getInventoryArray(Creatures c){
        JSONArray array = new JSONArray();
        for(InventoryItem o : c.getInventory().getInventoryList()){
            if(o.getItem() instanceof Object){
                array.put(converter.toJSON((Object)o.getItem()));
            } else if(o.getItem() instanceof Weapon){
                array.put(converter.toJSON((Weapon)o.getItem()));
            } else if(o.getItem() instanceof Armor){
                array.put(converter.toJSON((Armor)o.getItem()));
            }
        }
        return array;
    }

    public JSONArray getValueArray(Anything any){
        JSONArray array = new JSONArray();
        for(Value v: any.getValues()){
            array.put(converter.toJSON(v));
        }
        return array;
    }

    public JSONArray getSkillArray(Creatures c){
        JSONArray array = new JSONArray();
        for(Skill v: c.getSkills()){
            array.put(converter.toJSON(v));
        }
        return array;
    }

    public JSONArray getStatusArray(Creatures c){
        JSONArray array = new JSONArray();
        for(Status v: c.getStatus()){
            array.put(converter.toJSON(v));
        }
        return array;
    }

    public void fillInventoryList(JSONObject json, Creatures c){
        for(java.lang.Object o: json.getJSONArray(CREATURE_INVENTORY_KEY)){
            JSONObject obj = new JSONObject(o.toString());
            Type type = new Type(obj.getString(ANYTHING_TYPE_KEY));

            if(type.isType(Type.OBJECT)){
                c.getInventory().addItemToInventory(new ServerInventoryItem(converter.toObject(new JSONObject(o.toString()))));
            } else if(type.isType(Type.WEAPON)){
                c.getInventory().addItemToInventory(new ServerInventoryItem(converter.toWeapon(new JSONObject(o.toString()))));
            } else if(type.isType(Type.ARMOR)){
                c.getInventory().addItemToInventory(new ServerInventoryItem(converter.toArmor(new JSONObject(o.toString()))));
            }
        }
    }

    public void fillSkillList(JSONObject json, Creatures c){
        for(java.lang.Object o: json.getJSONArray(CREATURE_SKILLS_KEY)){
            c.getSkills().add(converter.toSkill(new JSONObject(o.toString())));
        }
    }

    public void fillStatusList(JSONObject json, Creatures c){
        for(java.lang.Object o: json.getJSONArray(CREATURE_STATUS_KEY)){
            c.getStatus().add(converter.toStatus(new JSONObject(o.toString())));
        }
    }

    public void fillValueList(JSONObject json, Anything any){
        for(java.lang.Object o: json.getJSONArray(ANYTHING_VALUES_KEY)){
            any.addValue(converter.toValue(new JSONObject(o.toString())));
        }
    }

    @Override
    public void setJSONConverter(JSONConverter converter) {
        this.converter = converter;
    }
}
