package com.morchul.model.models;

import com.morchul.action.Action;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Wearable;

public class Weapon extends Wearable {

    private int damage;

    public Weapon(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, boolean stackable, boolean dropable, Action pullOnAction, int damage) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, stackable, dropable, pullOnAction);
        this.damage = damage;
    }

    public int getDamage(){return damage;}
}
