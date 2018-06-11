package com.morchul.json;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Creatures;
import org.json.JSONArray;
import org.json.JSONObject;

public interface JSONArrayHelper {

    JSONArray getInventoryArray(Creatures c);
    JSONArray getValueArray(Anything any);
    JSONArray getSkillArray(Creatures c);
    JSONArray getStatusArray(Creatures c);
    void fillInventoryList(JSONObject json, Creatures c);
    void fillSkillList(JSONObject json, Creatures c);
    void fillStatusList(JSONObject json, Creatures c);
    void fillValueList(JSONObject json, Anything any);
    void setJSONConverter(JSONConverter converter);
}
