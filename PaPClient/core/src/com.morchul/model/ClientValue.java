package com.morchul.model;

import com.morchul.connections.StaticServerInterface;
import com.morchul.connections.message.MessageModelCreator;
import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class ClientValue implements Value {

    public ClientValue() {
        //TODO Standard Constructor
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getValue() {
        return 0;
    }

    @Override
    public void setValue(int value) {

    }

    @Override
    public void changeValue(Anything any, Creatures owner, String valueName,  int value) {
        StaticServerInterface.sendMessage(MessageModelCreator.createValueValueChangedMessage(any,owner,valueName, value));
    }
}
