package com.morchul.model.abstractmodels;

import com.morchul.action.Action;
import com.morchul.model.Type;

public abstract class Wearable extends Objects {

    protected Action pullOnAction;

    protected Wearable(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, boolean stackable, boolean dropable, Action pullOnAction) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, stackable, dropable);
        this.pullOnAction = pullOnAction;
    }

    public Action getPullOnAction(){return pullOnAction;}
}
