package com.morchul.model;

import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Creatures;

public class ClientCreatures extends Creatures {

    public ClientCreatures(String name, String uuid, String gameUUID, String imageName, String description, String masterDescription, Type type, int hp, int maxHp, int mp, int maxMp, int reaction, int will, int strength, int resistance, boolean immortal) {
        super(name, uuid, gameUUID, imageName, description, masterDescription, type, hp, maxHp, mp, maxMp, reaction, will, strength, resistance, immortal);
    }

    @Override
    public void dead() {
        //TODO
    }

    @Override
    public void setHp(int hp) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(hp, this, HP));
    }

    @Override
    public void setMaxHp(int maxHp) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(maxHp, this, MAX_HP));
    }

    @Override
    public void setMp(int mp) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(mp, this, MP));
    }

    @Override
    public void setMaxMp(int maxMp) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(maxMp, this, MAX_MP));
    }

    @Override
    public void setReaction(int reaction) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(reaction, this, REACTION));
    }

    @Override
    public void setWill(int will) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(will, this, WILL));
    }

    @Override
    public void setStrength(int strength) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(strength, this, STRENGTH));
    }

    @Override
    public void setResistance(int resistance) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueChangedMessage(resistance, this, RESISTANCE));
    }
}
