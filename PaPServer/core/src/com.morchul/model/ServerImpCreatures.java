package com.morchul.model;

import com.morchul.model.abstractmodels.Creatures;

public class ServerImpCreatures extends Creatures {

    public ServerImpCreatures(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, int hp, int maxHp, int mp, int maxMp, int reaction, int will, int strength, int resistance, boolean immortal) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, hp, maxHp, mp, maxMp, reaction, will, strength, resistance, immortal);
    }

    @Override
    public void setHp(int hp) {
        this.hp = (hp > maxHp) ? maxHp : hp;
        if(this.hp <= 0) {
            if(immortal)
                this.hp = 1;
            else
                dead();
        }
    }

    @Override
    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
        if(hp > maxHp)
            hp = maxHp;
    }

    @Override
    public void setMp(int mp) {
        this.mp = (mp > maxMp) ? maxMp : mp;
    }

    @Override
    public void setMaxMp(int maxMp) {
        this.maxMp = maxMp;
        if(mp > maxMp)
            mp = maxMp;
    }

    @Override
    public void setReaction(int reaction) {
        this.reaction = reaction;
    }

    @Override
    public void setWill(int will) {
        this.will = will;
    }

    @Override
    public void setStrength(int strength) {
        this.strength = strength;
    }

    @Override
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
}
