package com.morchul.model.models;

import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;

public class Skill extends Anything {

    private Type needWeapon;

    public Skill(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, Type needWeapon) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type);
        this.needWeapon = needWeapon;
    }

    public Type needWeapon(){return needWeapon;}
}
