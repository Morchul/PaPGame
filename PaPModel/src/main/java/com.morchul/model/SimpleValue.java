package com.morchul.model;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public class SimpleValue implements Value {

    private String name;
    private int value;
    public SimpleValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public void changeValue(Anything any, Creatures owner, String valueName, int value) {

    }

}
