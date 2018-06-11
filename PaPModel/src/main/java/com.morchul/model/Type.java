package com.morchul.model;

public class Type {

    public static final String ANYTHING = "1";
    public static final String CREATURES = "11";
    public static final String NPC = "111";
    public static final String DEALER = "1111";
    public static final String MONSTER = "1112";
    public static final String PLAYER = "112";
    public static final String GAME_MASTER = "113";
    public static final String OBJECTS = "12";
    public static final String OBJECT = "121";
    public static final String WEARABLE = "122";
    public static final String WEAPON = "1221";
    public static final String SWORD = "12211";
    public static final String SPEAR = "12212";
    public static final String BOW = "12213";
    public static final String AXE = "12214";
    public static final String KNIFE = "12215";
    public static final String MACE = "12216";
    public static final String ARMOR = "1222";
    public static final String SHIELD = "12221";
    public static final String HEAD_ARMOR = "12222";
    public static final String CHEST_ARMOR = "12223";
    public static final String LEG_ARMOR = "12224";
    public static final String SKILLS = "13";
    public static final String STATUS = "14";

    private String identifier;
    public Type(String identifier){
        this.identifier = identifier;
    }

    public boolean isType(Type type){
        return isType(type.identifier);
    }

    public boolean isType(String otherIdentifier){
        if(this.identifier.length() < otherIdentifier.length()) return false;
        return identifier.startsWith(otherIdentifier);
    }

    public String getIdentifier(){
        return identifier;
    }
}
