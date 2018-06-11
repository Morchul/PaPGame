package com.morchul.model.models;

import com.morchul.action.Action;
import com.morchul.model.DurationListener;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;

import java.util.ArrayList;
import java.util.List;

public class Status extends Anything {

    private Action action;
    private int duration;
    private List<DurationListener> listener;

    public Status(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, Action action, int duration) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type);
        this.action = action;
        this.duration = duration;
        listener = new ArrayList<>();
    }

    public void addListener(DurationListener l){
        listener.add(l);
    }

    public boolean action(Creatures target){
        action.action(this,target);
        for(DurationListener l: listener){
            l.valueChanged(--duration);
        }
        return duration != 0;
    }

    public Action getAction(){return action;}
    public int getDuration(){return duration;}
}
