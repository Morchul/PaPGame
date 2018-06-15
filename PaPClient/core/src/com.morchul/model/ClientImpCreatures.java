package com.morchul.model;

import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Creatures;

public class ClientImpCreatures extends Creatures {

    public ClientImpCreatures(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, int hp, int maxHp, int mp, int maxMp, int reaction, int will, int strength, int resistance, boolean immortal) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, hp, maxHp, mp, maxMp, reaction, will, strength, resistance, immortal);
    }

    @Override
    public void setHp(int hp) {
        if(hp != this.hp) {
            int i = (hp > maxHp) ? maxHp : hp;
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(i, this, HP));
        }
    }

    @Override
    public void setMaxHp(int maxHp) {
        if(maxHp != this.maxHp)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(maxHp, this, MAX_HP));
    }

    @Override
    public void setMp(int mp) {
        if(mp != this.mp) {
            int i = (mp > maxMp) ? maxMp : mp;
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(i, this, MP));
        }
    }

    @Override
    public void setMaxMp(int maxMp) {
        if(maxMp != this.maxMp)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(maxMp, this, MAX_MP));
    }

    @Override
    public void setReaction(int reaction) {
        if(reaction != this.reaction)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(reaction, this, REACTION));
    }

    @Override
    public void setWill(int will) {
        if(will != this.will)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(will, this, WILL));
    }

    @Override
    public void setStrength(int strength) {
        if(strength != this.strength)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(strength, this, STRENGTH));
    }

    @Override
    public void setResistance(int resistance) {
        if(resistance != this.resistance)
            StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(resistance, this, RESISTANCE));
    }
}
