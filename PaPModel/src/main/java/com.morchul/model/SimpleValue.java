package com.morchul.model;

public class SimpleValue implements Value {

    private String name;
    private int value;
    public SimpleValue(String name, int value) {
        this.name = name;
        this.value = value;
    }
    public SimpleValue(String name){
        this(name, 0);
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
}
