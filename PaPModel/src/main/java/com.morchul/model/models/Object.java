package com.morchul.model.models;

import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Objects;

public class Object extends Objects {

    public Object(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, boolean stackable, boolean dropable) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, stackable, dropable);
    }
}
