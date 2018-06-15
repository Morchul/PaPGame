package com.morchul.model;

import com.morchul.model.abstractmodels.Anything;
import com.morchul.model.abstractmodels.Creatures;

public interface Value {
    String getName();
    int getValue();
    void setValue(int value);
    void changeValue(Anything any, Creatures owner, String valueName, int value);
}
