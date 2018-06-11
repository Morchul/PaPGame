package com.morchul.model.models;

import com.morchul.inventory.Inventory;
import com.morchul.model.CreaturesListener;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class Creatures extends Anything {

    private int hp;
    private int maxHp;
    private int mp;
    private int maxMp;
    private int reaction;
    private int will;
    private int strength;
    private int resistance;
    private boolean immortal;
    private Inventory inventory;
    private ObservableList<Skill> skills;
    private ObservableList<Status> statuses;
    private List<CreaturesListener> listeners;

    public Creatures(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, int hp, int maxHp, int mp, int maxMp, int reaction, int will, int strength, int resistance, boolean immortal) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type);
        this.hp = hp;
        this.maxHp = maxHp;
        this.mp = mp;
        this.maxMp = maxMp;
        this.reaction = reaction;
        this.will = will;
        this.strength = strength;
        this.resistance = resistance;
        this.immortal = immortal;
        inventory = new Inventory();
        skills = FXCollections.observableArrayList();
        statuses = FXCollections.observableArrayList();
    }

    public void addListener(CreaturesListener l){listeners.add(l);}
    public int getHp(){return hp;}
    public void setHp(int hp){this.hp = hp;changeEvent();}
    public int getMaxHp(){return maxHp;}
    public void setMaxHp(int maxHp){this.maxHp = maxHp;changeEvent();}
    public int getMp(){return mp;}
    public void setMp(int mp){this.mp = mp;changeEvent();}
    public int getMaxMp(){return maxMp;}
    public void setMaxMp(int maxMp){this.maxMp = maxMp;changeEvent();}
    public int getReaction(){return reaction;}
    public void setReaction(int reaction){this.reaction = reaction;changeEvent();}
    public int getWill(){return will;}
    public void setWill(int will){this.will = will;changeEvent();}
    public int getStrength(){return strength;}
    public void setStrength(int strength){this.strength = strength;changeEvent();}
    public int getResistance(){return resistance;}
    public void setResistance(int resistance){ this.resistance = resistance; changeEvent();}
    public Inventory getInventory(){return inventory;}
    public ObservableList<Skill> getSkills(){return skills;}
    public void addSkill(Skill skill){skills.add(skill);}
    public ObservableList<Status> getStatus(){return statuses;}
    public void addStatus(Status status){statuses.add(status);}
    public void removeStatus(Status status){statuses.remove(status);}
    public boolean isImmortal(){return immortal;}
    public void changeEvent(){
        for(CreaturesListener l: listeners){
            l.changeEvent();
        }
    }

}
