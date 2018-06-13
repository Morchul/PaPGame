package com.morchul.model.models;

import com.morchul.model.Type;
import com.morchul.model.abstractmodels.Creatures;

public class SimpleCreatures extends Creatures {


    public SimpleCreatures(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, int hp, int maxHp, int mp, int maxMp, int reaction, int will, int strength, int resistance, boolean immortal) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, hp, maxHp, mp, maxMp, reaction, will, strength, resistance, immortal);
    }

    @Override
    public void dead() {
        //TODO
    }

    @Override
    public void setHp(int hp) { this.hp = hp; }

    @Override
    public void setMaxHp(int maxHp) { this.maxHp = maxHp; }

    @Override
    public void setMp(int mp) { this.mp = mp; }

    @Override
    public void setMaxMp(int maxMp) { this.maxMp = maxMp; }

    @Override
    public void setReaction(int reaction) { this.reaction = reaction; }

    @Override
    public void setWill(int will) { this.will = will; }

    @Override
    public void setStrength(int strength) { this.strength = strength; }

    @Override
    public void setResistance(int resistance) { this.resistance = resistance; }
}
