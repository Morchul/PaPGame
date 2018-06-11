package model;

import java.util.HashMap;
import java.util.Map;

public class TypeMap {

    public static Map<String, String> typeMap = new HashMap<>();
    static {
        typeMap.put("ANYTHING", "1");
        typeMap.put("CREATURES" , "11");
        typeMap.put("NPC" ,"111");
        typeMap.put("DEALER","1111");
        typeMap.put("MONSTER","1112");
        typeMap.put("PLAYER","112");
        typeMap.put("OBJECTS","12");
        typeMap.put("OBJECT","121");
        typeMap.put("WEARABLE","122");
        typeMap.put("WEAPON","1221");
        typeMap.put("SWORD","12211");
        typeMap.put("SPEAR","12212");
        typeMap.put("BOW","12213");
        typeMap.put("AXE","12214");
        typeMap.put("KNIFE","12215");
        typeMap.put("MACE","12216");
        typeMap.put("ARMOR","1222");
        typeMap.put("SHIELD","12221");
        typeMap.put("HEAD_ARMOR","12222");
        typeMap.put("CHEST_ARMOR","12223");
        typeMap.put("LEG_ARMOR","12224");
        typeMap.put("SKILLS","13");
        typeMap.put("STATUS","14");
    }
}
