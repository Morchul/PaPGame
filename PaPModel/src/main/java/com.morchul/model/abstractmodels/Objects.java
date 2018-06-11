package com.morchul.model.abstractmodels;

import com.morchul.model.Type;

public abstract class Objects extends Anything {
    protected boolean stackable;
    protected boolean dropable;

    protected Objects(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, boolean stackable, boolean dropable) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type);
        this.stackable = stackable;
        this.dropable = dropable;
    }

    public boolean isStackable(){return stackable;}
    public boolean isDropable(){return dropable;}
}
