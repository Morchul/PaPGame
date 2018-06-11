package com.morchul.model.abstractmodels;

import com.morchul.model.ChangeListener;
import com.morchul.model.Type;
import com.morchul.model.Value;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public abstract class Anything {

    protected final String UUID;
    protected final String name;
    protected final String imagePath;
    protected final Type type;
    protected String gameUUID;
    protected ObservableList<Value> values;
    protected String description;
    protected String masterDescription;
    protected List<ChangeListener> listener;

    protected Object card;

    protected Anything(String name, String uuid, String gameUUID, String imagePath, String description, String masterDescription, Type type){
        this.name = name;
        this.UUID = uuid;
        this.gameUUID = gameUUID;
        this.imagePath = imagePath;
        this.type = type;
        this.description = description;
        this.masterDescription = masterDescription;
        values = FXCollections.observableArrayList();
        listener = new ArrayList<>();
    }

    public Object getCard(){
        return card;
    }

    public void setCard(Object card){
        this.card = card;
    }

    public void destroy(){
        destroyCard();
        for(ChangeListener l : listener){
            l.destroyEvent();
        }
    }

    public void destroyCard(){
        for(ChangeListener l : listener){
            l.destroyCardEvent();
        }
    }

    public void addListener(ChangeListener l){listener.add(l);}
    public String getName(){return name;}
    public String getUUID(){return UUID;}
    public String getGameUUID(){return gameUUID;}
    public void setGameUUID(String uuid){gameUUID = uuid;}
    public String getImagePath(){return imagePath;}
    public ObservableList<Value> getValues(){return values;}
    public void addValue(Value v){values.add(v);}
    public String getDescription(){return description;}
    public String getMasterDescription(){return masterDescription;}
    public Type getType(){return type;}
}
