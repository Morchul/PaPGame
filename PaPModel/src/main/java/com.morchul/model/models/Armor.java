package com.morchul.model.models;

import com.morchul.action.Action;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Wearable;

public class Armor extends Wearable {

    private int armorValue;

    public Armor(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, boolean stackable, boolean dropable, Action pullOnAction, int armorValue) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, stackable, dropable, pullOnAction);
        this.armorValue = armorValue;
    }

    public int getArmorValue(){return armorValue;}
}
