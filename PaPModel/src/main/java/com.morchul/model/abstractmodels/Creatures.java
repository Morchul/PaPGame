package com.morchul.model.abstractmodels;

import com.morchul.inventory.Inventory;
import com.morchul.model.CreaturesListener;
import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.models.Skill;
import com.morchul.model.models.Status;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public abstract class Creatures extends Anything {

    public static final String HP = "hp";
    public static final String MAX_HP = "maxHp";
    public static final String MP = "mp";
    public static final String MAX_MP = "maxMp";
    public static final String REACTION = "reaction";
    public static final String WILL = "will";
    public static final String STRENGTH = "strength";
    public static final String RESISTANCE = "resistance";

    protected int hp;
    protected int maxHp;
    protected int mp;
    protected int maxMp;
    protected int reaction;
    protected int will;
    protected int strength;
    protected int resistance;
    protected boolean immortal;
    protected Inventory inventory;
    protected ObservableList<Skill> skills;
    protected ObservableList<Status> statuses;
    protected List<CreaturesListener> listeners;

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
        listeners = new ArrayList<>();
    }

    public abstract void dead();
    public void addListener(CreaturesListener l){listeners.add(l);}
    public int getHp(){return hp;}
    public abstract void setHp(int hp);
    public int getMaxHp(){return maxHp;}
    public abstract void setMaxHp(int maxHp);
    public int getMp(){return mp;}
    public abstract void setMp(int mp);
    public int getMaxMp(){return maxMp;}
    public abstract void setMaxMp(int maxMp);
    public int getReaction(){return reaction;}
    public abstract void setReaction(int reaction);
    public int getWill(){return will;}
    public abstract void setWill(int will);
    public int getStrength(){return strength;}
    public abstract void setStrength(int strength);
    public int getResistance(){return resistance;}
    public abstract void setResistance(int resistance);
    public void setValue(String valueName, int value){
        switch (valueName){
            case HP: hp = value; break;
            case MAX_HP: maxHp = value; break;
            case MP: mp = value; break;
            case MAX_MP: maxMp = value; break;
            case REACTION: reaction = value; break;
            case WILL: will = value; break;
            case STRENGTH: strength = value; break;
            case RESISTANCE: resistance = value; break;
        }
        changeEvent();
    }
    public Inventory getInventory(){return inventory;}
    public synchronized ObservableList<Skill> getSkills(){return skills;}
    public void addSkill(Skill skill){getSkills().add(skill);}
    public void removeSkill(Skill skill){skill.destroy();getSkills().remove(skill);}
    public Skill getSkillByGameUUID(String gameUUID){
        for(Skill s: getSkills()){
            if(s.getGameUUID().equals(gameUUID))
                return s;
        }
        return null;
    }
    public synchronized ObservableList<Status> getStatus(){return statuses;}
    public void addStatus(Status status){getStatus().add(status);}
    public void removeStatus(Status status){status.destroy();getStatus().remove(status);}
    public Status getStatusByGameUUID(String gameUUID){
        for(Status s: getStatus()){
            if(s.getGameUUID().equals(gameUUID))
                return s;
        }
        return null;
    }
    public boolean isImmortal(){return immortal;}
    public void changeEvent(){
        for(CreaturesListener l: listeners){
            l.changeEvent();
        }
    }

}
